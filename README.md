# TextAPI ![JDK 1.7](https://img.shields.io/badge/JDK-1.7-orange.png) ![Bukkit 1.12](https://img.shields.io/badge/Bukkit-1.12-yellow.png)

### _Title and action bar APIs for Bukkit._

At the moment, Bukkit and Spigot API do not provide a way to use two important features that have been available since the 
release of Minecraft 1.8: titles and custom action bar messages. This extremely lightweight library aims to provide developers
with an easy way to use those features, without having to mess with NMS or CraftBukkit internals.

## Use with Maven
If you use Maven, we recommend adding TextAPI as a dependency in the `<dependencies>` section of your POM:
```xml
<dependency>
  <groupId>io.github.theluca98</groupId>
  <artifactId>TextAPI</artifactId>
  <version>1.12-R2.1</version>
</dependency>
```
You can also allow Maven to automatically download TextAPI by adding our repository to the `<repositories>` section:
```xml
<repository>
  <id>textapi-repo</id>
  <url>https://dl.bintray.com/luca98/plugins/</url>
</repository>
```
**Use the [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin) to include TextAPI in your plugin JAR.**

## Download
For those who wish to import the library manually via the IDE instead of using Maven, a pre-compiled JAR can be downloaded from
the [Releases](https://github.com/TheLuca98/TextAPI/releases) page.

## Tutorial
Creating a Title instance and sending it to a player is very simple:
```java
Title title = new Title(title, subtitle, fadeIn, stay, fadeOut);
title.send(player);
```
Action bar messages work similarly:
```java
ActionBar bar = new ActionBar("Hello world");
bar.send(player);
```
The full JavaDocs are automatically generated when compiling the project.

## Compile
If you have [Git](https://git-scm.com) and [Maven](https://maven.apache.org) installed, downloading and compiling TextAPI is
fairly easy:
```
git clone https://github.com/TheLuca98/TextAPI.git
cd TextAPI
mvn
```
This will compile and install TextAPI into your local Maven repository as well as generating the JavaDocs, which you will find
in the subdirectory `target/site/apidocs`.

## Notes
Because this plugin doesn't directly depend on NMS classes thanks to reflection, except in the case of major changes, it should
be compatible between Minecraft/Spigot updates. If it does break, I will try to update it as soon as possible (no promises
though).

## License
This library is published under the terms of the GNU Lesser General Public License, version 3.
