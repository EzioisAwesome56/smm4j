# With the closure of the smm bookmark site, this library is useless and will only remain for historical purposes.
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
    <version>2.2</version>
</dependency>
```
## Usage Update note
Since version 2.0, some functions return different things from version 1.x. Please update your code if you are effected by this
## Usage
getLevel() takes a level ID as a string and returns a level object. From there, you can use Level.name(), level.date(), etc, to get information about the submitted level id.<br>
GetRandomLevelID() takes no arguments and returns a persudo random level id based on the Recommended levels page.<br>
<b>Both of the above functions throw a NullPointerExeception if nintendo is rate limiting you</b><br>
 makeUrl() takes a level id for arguments and returns the bookmark page for that level.
 ## Special thanks
 Thanks to [Gonson777](https://github.com/Godson777/) for assisting in creation of this library. He did almost all of the work with getting star counts, while rewriting a lot of everything else to make it cleaner. Thanks man!
