# SMM4j
Super Mario Maker for Java (or smm4j), is a basic library that
gathers information about levels from the bookmark site using a level id.
It is powered by jsoup.
## Notice
Worse come to worse, your ip may be unable to use the site for an hour if you send too many requests. Just be careful
## Installation
First, add this to your maven pom.xml
```xml
<repository>
       <id>jitpack.io</id>
       <url>https://jitpack.io</url>
</repository>
```
Then just add the repo itself
```xml
<dependency>
    <groupId>com.github.EzioisAwesome56</groupId>
    <artifactId>smm4j</artifactId>
    <version>1.0</version>
</dependency>
```
## Usage
Call getLevel(id), replacing ID with your level id as a string.
It will return a String[] with 7 entires.