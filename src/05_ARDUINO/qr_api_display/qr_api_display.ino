#include <ArduinoJson.h>
#include <ArduinoJson.hpp>
#include <qrcode.h>
#include <Wire.h>			
#include <Adafruit_GFX.h>	
#include <Adafruit_SSD1306.h>	
#include <WiFiNINA.h>

#define ANCHO 128
#define ALTO 64
#define OLED_RESET -1

Adafruit_SSD1306 oled(ANCHO, ALTO, &Wire, OLED_RESET);
QRCode qrcode;
//WiFiSSLClient client;


bool wifiConnected = false;
int pixelScale = 2;

void displayText(char* text, int time){
  oled.clearDisplay();
  oled.setTextColor(WHITE);
  oled.setTextSize(2);
  oled.setCursor(0, 0);	
  oled.print(text); 
  oled.display(); 
  delay(time);
}

void startWifi(){
  char ssid[] = "WifiHogar";
  char pass[] = "112358juan";
  int intentos = 1;

  while (WiFi.begin(ssid, pass) != WL_CONNECTED) {
    oled.clearDisplay();
    oled.setTextColor(WHITE);
    oled.setTextSize(2);
    oled.setCursor(0, 0);	
    oled.print("Conectando"); 
    oled.setCursor(15, 35);	
    oled.print(intentos);
    oled.display(); 
    delay(1000);

    intentos++;
  }
  wifiConnected = true;
}

void printQR(char* text){
  uint8_t qrcodeData[qrcode_getBufferSize(3)];
  qrcode_initText(&qrcode, qrcodeData, 3, 0, text);

  displayText("Generando QR..", 1000);

  oled.clearDisplay();
  for (uint8_t y = 0; y < qrcode.size; y++) {
    for (uint8_t x = 0; x < qrcode.size; x++) {
      if (qrcode_getModule(&qrcode, x, y)) {
        oled.fillRect((x + 18) * pixelScale, (y + 2) * pixelScale, pixelScale, pixelScale, WHITE);
      }
    }
  }
  oled.display();  
}

char* getData(){
  char server[] = "my-json-server.typicode.com"; // Servidor de la API
  String resource = "/Desireless/demo-c/data"; // Recurso de la API
  int port = 443; // Puerto para conexiones HTTPS

  WiFiSSLClient client;
  if (client.connect(server, port)) {
    client.println("GET " + resource + " HTTP/1.1");
    client.println("Host: " + String(server));
    client.println("Connection: close");
    client.println();

    while (client.connected()) {
      if (client.available()) {
        String line = client.readStringUntil('\n');
        if (line == "\r") {
          break;
        }
      }
    }

    String json = "";
    while (client.available()) {
      json += (char)client.read();
    }

    DynamicJsonDocument doc(1024);
    deserializeJson(doc, json);

    JsonObject obj = doc[0];

    const char* value = obj["key"];

    char* valueCopy = strdup(value);

    return valueCopy;
  } else {
    return strdup("Error al conectar con el servidor");
  }

}



void setup(){
  Wire.begin();
  oled.begin(SSD1306_SWITCHCAPVCC, 0x3C);

  startWifi();

  char* textToPrint = getData();
  
  printQR(textToPrint);
  
  free(textToPrint);
  
}

void loop(){}
