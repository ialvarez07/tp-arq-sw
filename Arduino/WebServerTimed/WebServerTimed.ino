#include <Ethernet.h>
#include <TimeLib.h>

#define TIME_HEADER  "T"   // Header tag for serial time sync message
#define TIME_REQUEST  7    // ASCII bell character requests a time sync message

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED }; //physical mac address
byte ip[] = { 192, 168, 0, 200 }; // ip en lan

EthernetServer server(80); //server port

String readString = String(100); //string for fetching data from address

void setup() {
  // Open serial communications and wait for port to open:
  Serial.begin(9600);
  while (!Serial) ;

  // start the Ethernet connection and the server:
  //if(!Ethernet.begin(mac)) {
    Ethernet.begin(mac, ip);
  //}
  
  server.begin();
  Serial.print("Server is at ");
  Serial.println(Ethernet.localIP());

  setSyncProvider( requestSync);  //set function to call when sync required
  Serial.println("Waiting for sync message");
}

void loop(){
  if (Serial.available()) {
    processSyncMessage();
  }
  
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
          Serial.println(readString); //Comentar despues de debug

          if (timeStatus()!= timeNotSet) {
            if(readString.indexOf("GET") >= 0 && (readString.indexOf("/temp") >= 0 || readString.indexOf("/temperatura") >= 0) ) {
              client.println("HTTP/1.1 200 OK");
              client.println("Content-Type: application/json;charset=utf-8");
              client.println("Server: Arduino");
              client.println("Connection: close");
              client.println();
              client.print("{\"temperatura\":23.2,");
              time_t t = now();
              //client.print("\"time\":{");
              client.print("\"time\":");
              /*client.print("\"anio\":");
              client.print(year(t));
              client.print(", \"mes\":");
              client.print(month(t));
              client.print(", \"dia\":");
              client.print(day(t));
              client.print(", \"hora\":");
              client.print(hour(t));
              client.print(", \"minutos\":");
              client.print(minute(t));
              client.print(", \"segundos\":");
              client.print(second(t));
              client.print(", \"secs\":");*/
              client.print(t);
              //client.println("}}");
              client.println("}");
              
              client.println();
            
              delay(1000);
            }
          }
          
          
          client.stop();
          
          readString="";
           
        }
      }
    }
  }
}

time_t requestSync()
{
  Serial.write(TIME_REQUEST);  
  return 0; // the time will be sent later in response to serial mesg
}

void processSyncMessage() {
  unsigned long pctime;
  const unsigned long DEFAULT_TIME = 1357041600; // Jan 1 2013

  if(Serial.find(TIME_HEADER)) {
     pctime = Serial.parseInt();
     if( pctime >= DEFAULT_TIME) { // check the integer is a valid time (greater than Jan 1 2013)
       setTime(pctime); // Sync Arduino clock to the time received on the serial port
       Serial.println("Tiempo Configurado");
     }
  }
}
