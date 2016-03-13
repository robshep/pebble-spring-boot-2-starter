#Pebble Spring Boot starter for Pebble Template Engine

Spring Boot starter for autoconfiguring Pebble as an MVC ViewResolver.

## Basic Usage
Add the starter dependency to your pom.xml:
```XML
<dependency>
	<groupId>com.mitchellbosecke</groupId>
	<artifactId>pebble-spring-boot-starter</artifactId>
	<version>${version}</version>
</dependency>
```

This is enough for autoconfiguration to kick in. This includes:

* a Loader that will pick template files ending in ``.pebble`` from ``/templates/`` dir on the classpath
* a PebbleEngine with default settings, configured with the previous loader
* a ViewResolver that will output ``text/html`` in ``UTF-8``