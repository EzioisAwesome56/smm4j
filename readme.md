# SMM4j
Super Mario Maker for Java (or smm4j), is a basic library that
gathers information about levels from the bookmark site using a level id.
It is powered by jsoup.
## Notice
Too many requests to the bookmark site may cause your ip to be 403'd for an hour. This is basically nintendo's rate limiting. Just be careful
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
    <version>2.0</version>
</dependency>
```
## Usage
getLevel() takes your level id as a string for arguments, spits out a 7 entry long String[] with all the info you need<br>
makeUrl() takes your level id as input and returns the mario maker bookmark site url for that level<br>
GetRandomLevel() takes no arguments and returns a persudo random level based on the recommended courses page