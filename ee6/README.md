### config-singleton
example of using a JavaEE Singleton that contains configuration that can be used by other applications by injecting it. The PropertiesConfig singleton reads a properties file packages in its jar and exposes those properties.

* config-singleton.jar/com/jboss/examples/ee6/ejb/PropertiesConfig.java
 * This is a singleton that reads a properties file and then exposes it to applications that inject it
* config-singleton.jar/META-INF/ejb-jar.xml
 * This file defines an env-entry that is a properties file (config.properties) PropertiesConfig injects the name and reads the properties
* config-singleton.jar/build.xml
 * Ant build script
* config-singleton.jar/config.properties
 * This file contains some properties that PropertiesConfig will read
* config-singleton.jar/com/jboss/examples/test/TestSingleton.java
 * This is a singleton that will inject PropertiesConfig and list the properties that it read
* config-singleton.jar/com/jboss/examples/ee6/ejb/MBeanBase.java
 * <TODO> This contains code to bind a Singleton into JMX
