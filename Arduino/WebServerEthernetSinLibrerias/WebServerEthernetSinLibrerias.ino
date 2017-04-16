#include <Ethernet.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED }; //physical mac address
byte ip[] = { 192, 168, 0, 200 }; // ip en lan

EthernetServer server(80); //server port

String readString = String(100); //string for fetching data from address

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for native USB port only
  }

  // start the Ethernet connection and the server:
  //if(!Ethernet.begin(mac)) {
    Ethernet.begin(mac, ip);
  //}
  server.begin();
  Serial.print("server is at ");
  Serial.println(Ethernet.localIP());
}

void loop(){
  // Create a client connection
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        
        //read char by char HTTP request
        if (readString.length() < 100) {
          //store characters to string
          readString.concat(c);
        }
      
        //if HTTP request has ended
        if (c == '\n') {
          Serial.println(readString);
          
          if(readString.indexOf("GET") >= 0 && (readString.indexOf("/temp") >= 0 || readString.indexOf("/temperatura") >= 0) ) {
            client.println("HTTP/1.1 200 OK");
            client.println("Content-Type: application/json;charset=utf-8");
            client.println("Server: Arduino");
            client.println("Connection: close");
            client.println();
            client.println("[{\"tempIn\":23.2, \"tempOut\":16.8, \"unit\":\"Celcius\" }]");
            client.println();
          
            delay(1);
          }
          
          client.stop();
          
          readString="";
           
        }
      }
    }
  }
}


