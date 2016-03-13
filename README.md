#Pebble Spring Boot Starter
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

##Customizing Pebble
###Pebble extensions
Extensions defined as beans will be picked up and added to the PebbleEngine automatically:
```Java
public Extension myPebbleExtension1() {
   return new MyPebbleExtension1();
}

public Extension myPebbleExtension2() {
   return new MyPebbleExtension2();
}
```

###Customizing the Loader
The autoconfigurer looks for a bean named ``pebbleLoader`` in the context. You can define a custom loader with that name and it will be used to configure the default PebbleEngine:
```Java
public Loader<?> pebbleLoader() {
   return new MyCustomLoader();
}
```
PLEASE NOTE: this loader's prefix and suffix will be both overwritten when the ViewResolver is configured. You should use the externalized configuration for changing this properties.

###Customizing the PebbleEngine
Likewise, you can build a custom engine and make it the default by using the bean name ``pebbleEngine``:
```Java
public PebbleEngine pebbleEngine() {
   return new PebbleEngine.Builder().build();
}
```

###Customizing the ViewResolver
And the same goes for the ViewResolver, using the bean name ``pebbleViewResolver``: 
```Java
public PebbleViewResolver pebbleViewResolver() {
   return new PebbleViewResolver();
}
```

###Using Pebble for other tasks
The main role of this starter is to configure Pebble for generating MVC View results (the typical HTML). You may define more PebbleEngine/Loader beans for other usage patterns (like generating email bodies). Bear in mind that you should not reuse the default Loader for other Engine instances.