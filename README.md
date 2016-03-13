#Pebble Spring Boot starter
Spring Boot starter for autoconfiguring Pebble as an MVC ViewResolver.

##Basic Usage
Add the starter dependency to your pom.xml:
```XML
<dependency>
	<groupId>com.mitchellbosecke</groupId>
	<artifactId>pebble-spring-boot-starter</artifactId>
	<version>${version}</version>
</dependency>
```
Or build.gradle:
```Gradle
compile "com.mitchellbosecke:pebble-spring-boot-starter:$version"
```

This is enough for autoconfiguration to kick in. This includes:

* a Loader that will pick template files ending in ``.pebble`` from ``/templates/`` dir on the classpath
* a PebbleEngine with default settings, configured with the previous loader
* a ViewResolver that will output ``text/html`` in ``UTF-8``

PLEASE NOTE: the starter depends on ``spring-boot-starter-web`` but is marked as optional, you'll need to add the dependency yourself or configure Spring MVC appropiately.

##Boot externalized configuration
A number of properties can be defined in Spring Boot externalized configuration, eg. ``application.properties``, starting with the prefix ``pebble``. See the corresponding [PebbleProperties.java](https://github.com/PebbleTemplates/pebble-spring-boot-starter/blob/master/src/main/java/com/mitchellbosecke/pebble/boot/autoconfigure/PebbleProperties.java) for your starter version. Notable properties are:

* ``pebble.prefix``: defines the prefix that will be prepended to the mvc view name. Defaults to ``templates/``
* ``pebble.suffix``: defines the suffix that will be appended to the mvc view name. Defaults to ``.pebble``
* ``pebble.cache``: enables or disables PebbleEngine caches. Defaults to ``true``
* ``pebble.contentType``: defines the content type that will be used to configure the ViewResolver. Defaults to ``text/html``
* ``pebble.encoding``: defines the text enconding that will be used to configure the ViewResolver. Defaults to ``UTF-8``