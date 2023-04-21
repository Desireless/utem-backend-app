#include <qrcode.h>
#include <Wire.h>			// libreria para bus I2C
#include <Adafruit_GFX.h>		// libreria para pantallas graficas
#include <Adafruit_SSD1306.h>		// libreria para controlador SSD1306
#include <WiFiNINA.h>

char ssid[] = "WifiHogar";
char pass[] = "112358juan";
 
#define ANCHO 128			// reemplaza ocurrencia de ANCHO por 128
#define ALTO 64				// reemplaza ocurrencia de ALTO por 64
#define OLED_RESET -1			// necesario por la libreria pero no usado
Adafruit_SSD1306 oled(ANCHO, ALTO, &Wire, OLED_RESET);	// crea objeto

void setup() {
  Serial.begin(9600); // inicializa el puerto serie

/////////////////////////////////////////////////

  Wire.begin();					// inicializa bus I2C
  oled.begin(SSD1306_SWITCHCAPVCC, 0x3C);	// inicializa pantalla con direccion 0x3C
  
  // Create the QR code
  QRCode qrcode;
  uint8_t qrcodeData[qrcode_getBufferSize(3)];
  qrcode_initText(&qrcode, qrcodeData, 3, 0, "UTEM");


  oled.clearDisplay();			// limpia pantalla
  oled.setTextColor(WHITE);		// establece color al unico disponible (pantalla monocromo)
  oled.setCursor(0, 0);			// ubica cursor en inicio de coordenadas 0,0
  oled.setTextSize(2);
  

  int intentos = 1;
  while (WiFi.begin(ssid, pass) != WL_CONNECTED) {
    oled.clearDisplay();
    oled.print("Conectando"); 
    oled.setCursor(15, 25);	
    oled.print(intentos);
    oled.display(); 
    delay(1000);
  }
  oled.clearDisplay();
  oled.setCursor(12, 15); 
  oled.print("Generando QR...");
  oled.display(); 
  delay(1000);
  oled.clearDisplay();
  
  int scale = 2; // escala del tamaño del pixel a dibujar

  for (uint8_t y = 0; y < qrcode.size; y++) {
    for (uint8_t x = 0; x < qrcode.size; x++) {
      if (qrcode_getModule(&qrcode, x, y)) {
        // x + 10: para mover el qr hacia la derecha
        // (y + 2) para mover el qr hacia abajo
        oled.fillRect((x + 18) * scale, (y + 2) * scale, scale, scale, WHITE);
      }
    }
  }
  oled.display();  

}
 
void loop() {
  //oled.clearDisplay();			// limpia pantalla
  //oled.setTextColor(WHITE);		// establece color al unico disponible (pantalla monocromo)
  //oled.setCursor(0, 0);			// ubica cursor en inicio de coordenadas 0,0
  //oled.setTextSize(1);			// establece tamano de texto en 1
  //oled.print("Hola, han pasado:"); 	// escribe en pantalla el texto
  //oled.setCursor (10, 30);		// ubica cursor en coordenas 10,30
  //oled.setTextSize(2);			// establece tamano de texto en 2
  //oled.print(millis() / 1000);		// escribe valor de millis() dividido por 1000
  //oled.print(" seg.");			// escribe texto
  //oled.display();			// muestra en pantalla todo lo establecido anteriormente
}