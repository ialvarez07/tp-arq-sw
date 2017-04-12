/*
  This a simple example of the aREST Library for Arduino (Uno/Mega/Due/Teensy)
  using the Ethernet library (for example to be used with the Ethernet shield).
  See the README file for more details.

  Written in 2014 by Marco Schwartz under a GPL license.
*/

// Libraries
#include <SPI.h>
#include <Ethernet.h>
#include <aREST.h>
#include <avr/wdt.h>

// Enter a MAC address for your controller below.
byte mac[] = { 0x90, 0xA2, 0xDA, 0x0E, 0xFE, 0x40 };

// IP address in case DHCP fails
IPAddress ip(192,168,0,200);

// Ethernet server
EthernetServer server(80);

// Create aREST instance
aREST rest = aREST();

float valor;
int temperatura;

void setup(void)
{
  // Start Serial
  Serial.begin(9600);

  // Init variables and expose them to REST API
  temperatura = 24;
  rest.variable("temperatura",&temperatura);

  // Give name & ID to the device (ID should be 6 characters long)
  rest.set_id("007");
  rest.set_name("standartNerd-ASW");

  // Start the Ethernet connection and the server
//  if (Ethernet.begin(mac) == 0) {
//    Serial.println("Failed to configure Ethernet using DHCP");
    // no point in carrying on, so do nothing forevermore:
    // try to congifure using IP address instead of DHCP:
    Ethernet.begin(mac, ip);
//  }

  delay(1000);

  server.begin();
  Serial.print("server is at: ");
  Serial.println(Ethernet.localIP());

  // Start watchdog
  wdt_enable(WDTO_4S);
}

void loop() {

  // listen for incoming clients
  EthernetClient client = server.available();
  rest.handle(client);
  wdt_reset();

  valor = analogRead(A0);
  valor = (5.0*valor*100.0)/1024.0;
  temperatura = valor;
}
