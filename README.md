# TextAPI
At the moment, Bukkit and Spigot API do not provide a way to use
two important features that have been available with the release
of Minecraft 1.8: titles and custom action bar messages. This
extremely lightweight library aims to provide to developers an
easy way to use those features, without having to mess with NMS.

## Compile
In order to compile this library you need to have
[Apache Maven](https://maven.apache.org) installed on your
computer. Since TextAPI 2.0, CraftBukkit is no longer a required
dependency.

First, download a copy of this repository on your computer. If
you have Git installed, it is recommended to use `git clone`.

Once you have downloaded the repository, you can easily compile
TextAPI by running `mvn` (if that doesn't work, use `mvn clean
install`).

## Install
If you use Maven, make sure to include TextAPI as a dependency by
adding it to the `<dependencies>` section of your POM file:
```
<dependency>
    <groupId>io.github.theluca98</groupId>
    <artifactId>TextAPI</artifactId>
    <version>1.8.8-R2.0</version>
</dependency>
```
You might also need to include TextAPI in your JAR file by using
the [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/).

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
**Please refer to the [JavaDocs](https://theluca98.github.io/TextAPI/) for the full documentation.**

## Notes
Because this plugin doesn't directly depend on NMS classes thanks to
Reflection, except in the case of major changes, it should be compatible
between Minecraft/Spigot updates. If it does break, I will try to update
it as soon as possible (no promises though).

## License
This library is published under the terms of the GNU Lesser General
Public License, version 3. Please read the LICENSE file before
using this library.
