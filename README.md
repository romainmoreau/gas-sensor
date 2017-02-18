# Gas sensor
## Java clients
If you need to use the gas sensors in your own Java projects, you can use the Java clients. The only external dependency used by these clients is JSSC for handling the serial port communication.

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

To use a client, you just need to instanciate it and close it when it's not needed anymore.
You can find a way to instanciate all the existing clients [here](https://github.com/romainmoreau/gas-sensor/blob/master/gas-sensor-web/src/main/java/fr/romainmoreau/gassensor/web/JsscGasSensorClientConfiguration.java).
