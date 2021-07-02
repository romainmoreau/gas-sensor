# Gas sensor
[![Build Status](https://travis-ci.com/romainmoreau/gas-sensor.svg?branch=master)](https://travis-ci.com/romainmoreau/gas-sensor)

To build the projects, simply run `mvn clean install`.
## Web application
### Installation
After building the projects, the web application binary will be located at:
* `gas-sensor-web\target\gas-sensor-web.exe` **(Windows specific)**
* `gas-sensor-web\target\gas-sensor-web.jar` **(All OS)**

### Configuration
You need to create an `application.yml` next to the binary before running it:
```yml
ze07:
  gas-sensors: # Not required, activates the reading from the specified ports of ZE07 sensors if the jserialcomm profile is activated.
    -
      port-name: COM2 # Required
ze08:
  gas-sensors: # Not required, activates the reading from the specified ports of ZE08 sensors if the jserialcomm profile is activated.
    -
      port-name: COM3 # Required
zh03a:
  gas-sensors: # Not required, activates the reading from the specified ports of ZH03A sensors if the jserialcomm profile is activated.
    -
      port-name: COM4 # Required
zph01:
  gas-sensors: # Not required, activates the reading from the specified ports of ZPH01 sensors if the jserialcomm profile is activated.
    -
      port-name: COM5 # Required
mhz19:
  gas-sensors: # Not required, activates the reading from the specified ports of MH-Z19 sensors if the jserialcomm profile is activated.
    -
      port-name: COM6 # Required
si7021:
  gas-sensors: # Not required, activates the reading from the specified ports of Si7021 sensors if the jserialcomm profile is activated.
    -
      description: _Inside # Not required
      port-name: COM7 # Required
    -
      description: _Outside # Not required
      port-name: COM8 # Required
spring:
  profiles:
    active: jserialcomm # Profile jserialcomm activates the reading from the sensors via the serial port communication. Profile mock activates the reading from mock sensors.
  datasource: 
    url: jdbc:h2:~/gas-sensor-web;DB_CLOSE_ON_EXIT=FALSE # Not required, by default a memory H2 database is created. PostgreSQL JDBC URL are also supported.
  jpa:
    hibernate:
      ddl-auto: update # Not required, but if not added, you will have to create the tables by hand.
logging:
  file:
    name: gas-sensor-web.log # Not required, default is logging to console only.
server:
  port: 8081 # Not required, default is 8080.
```

### Usage
To run it, you can: 
* Double click on `gas-sensor-web.exe` **(Windows specific)**
* Run `java -jar gas-sensor-web.jar` **(All OS)**

## Java clients
If you need to use the gas sensors in your own Java projects, you can use the Java clients. The only external dependency used by these clients is jSerialComm for handling the serial port communication.

If you want to use all the clients, you can add to your POM this shortcut dependency:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-all</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```
You can also add the clients one by one:
* ZPH01:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-zph01</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```
* ZH03A:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-zh03a</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```
* ZE07:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-ze07</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```
* ZE08:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-ze08</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```
* MH-Z19:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-mhz19</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```
* Si7021:
```xml
<dependency>
	<groupId>fr.romainmoreau.gassensor</groupId>
	<artifactId>gas-sensor-client-si7021</artifactId>
	<version>0.1.0-SNAPSHOT</version>
</dependency>
```

To use a client, you just need to instanciate it and close it when it's not needed anymore.
You can find a way to instanciate all the existing clients [here](https://github.com/romainmoreau/gas-sensor/blob/master/gas-sensor-web/src/main/java/fr/romainmoreau/gassensor/web/JSerialCommGasSensorClientConfiguration.java).
