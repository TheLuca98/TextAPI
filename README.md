# TextAPI

#### _Title and action bar APIs for Bukkit._

![JDK 1.7](https://img.shields.io/badge/JDK-1.7-orange.png)
![Bukkit 1.9.2](https://img.shields.io/badge/Bukkit-1.9.2-blue.png)

At the moment, Bukkit and Spigot API do not provide a way to use
two important features that have been available with the release
of Minecraft 1.8: titles and custom action bar messages. This
extremely lightweight library aims to provide to developers an
easy way to use those features, without having to mess with NMS.

## Compile
If you have [Git](https://git-scm.com) and [Maven](https://maven.apache.org)
installed, downloading and compiling TextAPI is fairly easy:
```
git clone https://github.com/TheLuca98/TextAPI.git
cd TextAPI
mvn
```
This will compile and install TextAPI into your local Maven
repository as well as generating the JavaDocs, which you'll be
able to find in the subdirectory `target/site/apidocs`.

Alternatively, you can simply download the JAR from the
[Releases](https://github.com/TheLuca98/TextAOI/releases) page and
import it in your IDE.

## Install
If you use Maven, make sure to include TextAPI as a dependency by
adding it to the `<dependencies>` section of your POM file:
```
<dependency>
    <groupId>io.github.theluca98</groupId>
    <artifactId>TextAPI</artifactId>
    <version>1.9.2-R2.1</version>
</dependency>
```
You will also need to include TextAPI in your JAR file by using
the [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin).

## Usage
Creating a Title instance and sending it to a player is very simple:
```
Title title = new Title(title, subtitle, fadeIn, stay, fadeOut);
title.send(player);
```
Action bar messages work similarly:
```
ActionBar bar = new ActionBar("Hello world");
bar.send(player);
```
**Please refer to the [JavaDocs](https://theluca98.github.io/TextAPI)
for the full documentation.**

## Notes
Because this plugin doesn't directly depend on NMS classes thanks to
Reflection, except in the case of major changes, it should be compatible
between Minecraft/Spigot updates. If it does break, I will try to update
it as soon as possible (no promises though).

## License
This library is published under the terms of the GNU Lesser General
Public License, version 3. Please read the LICENSE file before
using this library.
