# TextAPI
At the moment, Bukkit and Spigot API do not provide a way to use
two important features that have been available with the release
of Minecraft 1.8: titles and custom action bar messages. This
extremely lightweigth library aims to provide to developers an
easy way to use those features, without having to mess with NMS.

## Compile
In order to compile this library you need to have
[Apache Maven](https://maven.apache.org) installed on your
computer.

First, download a copy of this repository on your computer. If
you have Git installed, we recommend using `git clone`.

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
    <version>1.8.3-SNAPSHOT</version>
</dependency>
```
You might also need to include TextAPI in your JAR file by using
the [Maven Shade Plugin](https://maven.apache.org/plugins/maven-shade-plugin/).

## Usage
Creating a Title instance is simple:
```
Title title = new Title(title, subtitle, fadeIn, stay, fadeOut);
```
And sending it to a player is simple as well:
```
title.send(player);
```
To send action bar messages we provide a simple utility static method:
```
ActionBar.send(player, message);
```
**For a complete documentation, please refer to the [JavaDocs](https://theluca98.github.io/TextAPI/).**

## Notes
Because this plugin depends on NMS classes, it might break each
time Minecraft (and Spigot) update. If that's the case, I will
try to update it as soon as possible, but I cannot make any
promises about this.

## License
This library is published under the terms of the GNU General
Public License version 3. Please read the LICENSE file before
using this library.
