package com.eziosoft.smm4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Smm4j {

    protected static String baseurl = "https://supermariomakerbookmark.nintendo.net/courses/";

    public static void main(String[] args){
        // check if arguments where provided
        if (args.length > 0){
            // then parse args
            if (args[0].equals("-cli")){
                Cli.cli(args);
            }
        }
    }

    // class to get level details
<<<<<<< HEAD
=======
    public static Level getLevel(String id){
        return Level.getLevel(id);
>>>>>>> 65f9bda3049df11c66a54fadad3d478336917fea
    }
}
