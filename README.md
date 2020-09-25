# HandcraftedBits Common Code

Common code used by HandcraftedBits projects.

# Usage

Add the following dependency to your `pom.xml` file:

```xml
<dependency>
  <groupId>com.handcraftedbits</groupId>
  <artifactId>handcraftedbits-commons</artifactId>
  <version>1.0.1-SNAPSHOT</version>
</dependency>
```

To route all logging through the included [Slf4j](http://www.slf4j.org/) appenders, make sure to exclude the following
dependencies:

```xml
<exclusion>
  <groupId>commons-logging</groupId>
  <artifactId>commons-logging</artifactId>
</exclusion>
<exclusion>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-api</artifactId>
</exclusion>
<exclusion>
  <groupId>org.apache.logging.log4j</groupId>
  <artifactId>log4j-core</artifactId>
</exclusion>
<exclusion>
  <groupId>log4j</groupId>
  <artifactId>log4j</artifactId>
</exclusion>
```

To route `java.util.logging`, make sure to call `LogInitializer.initialize()`.
