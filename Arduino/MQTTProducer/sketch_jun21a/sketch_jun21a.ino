#include <SPI.h>
#include <Ethernet.h>
#include <PubSubClient.h>

// Direccion MAC
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };

// IP del servidor
IPAddress mqtt_server(192, 168, 1, 25);

// Topic con el que trabajamos
const char* topicName = "measurement";

EthernetClient ethClient;
PubSubClient client(ethClient);

void setup()
{
  Serial.begin(9600);
  if (Ethernet.begin(mac) == 0) {
    Serial.println("Failed to configure Ethernet using DHCP");
  }
  client.setServer(mqtt_server, 1883);
}

void loop()
{
  if (!client.connected()) {
    Serial.print("Connecting ...\n");
    client.connect("Arduino Client");
  }
  else {
    // Envio
    float temp = (float)rand()/(float)(RAND_MAX/30);
    char buffer[10];
    dtostrf(temp,0, 0, buffer);
    client.publish(topicName, buffer);
  }
  // Tiempo entre envios (en ms)
  delay(5000);
}


