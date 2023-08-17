#include <ArduinoJson.h>
#include <ArduinoJson.hpp>
#include <qrcode.h>
#include <Wire.h>
#include <Adafruit_GFX.h>
#include <Adafruit_SSD1306.h>
#include <WiFiNINA.h>

/*
  Especifica parametros del dispositivo MKR WIFI 1010
*/
#define ANCHO 128
#define ALTO 64
#define OLED_RESET -1
char* SERIAL_NUMBER = "CCEEBC6B515438153202020FF131829";

Adafruit_SSD1306 oled(ANCHO, ALTO, &Wire, OLED_RESET);
QRCode qrcode;
//WiFiSSLClient client;

bool wifiConnected = false;
int pixelScale = 2;

/*
  Inicia la conexion a la red wifi especificada
*/
void startWifi() {
  char ssid[] = "WifiHogar";
  char pass[] = "112358juan";
  int count = 0;

  do {
    count++;

    displayMultiText("OK", "CONECTANDO A LA RED..");
    delay(500);

    if (count == 5) {
      displayMultiText("ERROR [RED]", "NO SE PUDO ESTABLECER CONEXION CON LA RED");
      delay(6000);
      return;
    }

  } while (WiFi.begin(ssid, pass) != WL_CONNECTED);

  wifiConnected = true;
}

/*
  Imprime pixeles que forman el QR a partir de una cadena de caracteres
*/
void printQR(char* text) {
  uint8_t qrcodeData[qrcode_getBufferSize(3)];
  qrcode_initText(&qrcode, qrcodeData, 3, 0, text);

  displayMultiText("OK", "GENERANDO QR...");
  delay(1500);

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

/*
  Concatena dos cadenas de texto con un ":" entre ellas. Ej: "token:ns"
  @Return char* 
*/
char* concatWithColon(const char* variable1, const char* variable2) {
  size_t len1 = strlen(variable1);

  size_t totalLength = len1 + strlen(":") + strlen(variable2) + 1;  // +1 para el carácter nulo '\0'

  char* result = (char*)malloc(totalLength);

  // Verificar si la memoria se asignó correctamente
  if (result == NULL) {
    // Manejo de error, en caso de que no haya suficiente memoria disponible
    return NULL;
  }

  strcpy(result, variable1);

  strcat(result, ":");

  strcat(result, variable2);

  return result;
}

/*
  Realiza peticion http para recibir token de la sala asignada
  @Return char* token
*/
char* getData() {
  char server[] = "my-json-server.typicode.com";  // Servidor de la API
  String resource = "/Desireless/demo-c/data";       // Recurso de la API
  int port = 443;                                    // Puerto para conexiones HTTPS
  /*
    FAKE API https://my-json-server.typicode.com/Desireless/demo-c/data devuelve:
    {
      "id": 1,
      "key": "hello"
    }
  */

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

    // Caso de respuesta vacia
    if (doc.size() > 0) {
      const char* value = obj["key"];

      char* result = strdup(value);

      return result;
    } else {
      displayMultiText("ERROR [SERVIDOR]", "RESPUESTA VACIA");
      delay(3500);
      return strdup("error");
    }


  } else {
    displayMultiText("ERROR [SERVIDOR]", "NO HUBO RESPUESTA");
    delay(3500);
    return strdup("error");
  }
}

/*
  Maneja la peticion http y verifica si existe un error
  @Return char* token
*/
char* handleApi() {
  char* result;

  displayMultiText("OK", "CONECTANDO CON EL SERVIDOR...");
  delay(1000);
  result = getData();

  if (strcmp(result, "error") != 0) {
    // Si la respuesta es correcta
    displayMultiText("OK", "DATOS RECIBIDOS");
    delay(1000);
    return result;
  }

  return strdup("error");
}

/*
  Imprime en pantalla dos cadenas de caracteres. La primera en la zona superior y la segunda en medio de la pantalla.
*/
void displayMultiText(char* firstText, char* secondText) {
  oled.clearDisplay();
  oled.setTextColor(WHITE);
  oled.setTextSize(1);
  oled.setCursor(0, 0);
  oled.print(firstText);
  oled.setCursor(0, 30);
  oled.print(secondText);
  oled.display();
}

// void displayMultiTextWithCounter(char* firstText, char* secondText, int counter) {
//   oled.clearDisplay();
//   oled.setTextColor(WHITE);
//   oled.setTextSize(1);
//   oled.setCursor(0, 0);
//   oled.print(firstText);
//   oled.setCursor(0, 20);
//   oled.print(secondText);
//   oled.setCursor(0, 55);
//   oled.print(counter);
//   oled.display();
// }

/*
  Genera la cadena de caracteres final a ser expuesta en codigo QR.
  Concatena token con el numero serial del Arduino.
  Verifica si token esta vacio o es nulo.
  @Return char* token
*/
char* generateQRData(char* token) {
  if (token == NULL || token[0] == '\0') {
    displayMultiText("ERROR [QR DATA]", "TOKEN VACIO O NULO");
    delay(3500);
    return concatWithColon("null", SERIAL_NUMBER);
  }

  char* finalString = concatWithColon(token, SERIAL_NUMBER);
  displayMultiText("OK TOKEN", finalString);
  delay(3500);


  return finalString;
}

void setup() {
  Wire.begin();
  oled.begin(SSD1306_SWITCHCAPVCC, 0x3C);

  displayMultiText("Trabajo de titulo", "Juan Pablo Bastias Barahona");
    delay(3500);

  startWifi();
  if (wifiConnected) {
    char* token = handleApi();
    displayMultiText("TOKEN OBTENIDO", token);
    delay(3500);
    char* data = generateQRData(token);
    printQR(data);

  } else {
    displayMultiText("SIN CONEXION", "GENERANDO TOKEN TEMPORAL..");
    delay(5500);
    char* data = generateQRData("notoken");
    printQR(data);
  }

  //free(textToPrint);
}

void loop() {
}
