package com.eziosoft.smm4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Smm4j {

    private static String baseurl = "https://supermariomakerbookmark.nintendo.net/courses/";

    public static void main(String[] args){
        // check if arguments where provided
        if (args.length > 0){
            // then parse args
            if (args[0].equals("-cli")){
                cli(args);
            }
        }
    }

    public static void cli(String[] args){
        // check if its blank
        String id;
        try{
            id = args[1];
        } catch (NullPointerException e){
            System.out.println("Error: you gave no level id!");
            return;
        }
        // pass id to getlevel
        String[] a = getLevel(id);
        // error?
        if (a[0].equals("error")){
            System.out.println("An error has occured!");
            return;
        }
        // print all the stuff
        System.out.println("NAME: "+a[0]);
        System.out.println("DIFF: "+a[1]);
        System.out.println("AUTHOR: "+a[2]);
    }

    // class to get level details
    public static String[] getLevel(String id){
        String[] error = {"error"};
        Document doc;
        // is the level id provided blank?
        if (id.equals(null)){
            return error;
        }
        // use jsoup to grab the document
        try {
            doc = Jsoup.connect(baseurl + id).userAgent("Mozilla/5.0 (Debian Stretch) Gecko/20100101 Firefox/63.0").get();
        } catch (IOException e){
            System.out.println("Error!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return error;
        }
        // is there an error?
        if (doc.select("div.error-description").equals("The page could not be found.")){
            String[] out = {"404"};
            return out;
        }
        // get just the course card
        Elements card = doc.select("div.course-card");
        // grab difficulty
        String diff = doc.select("div.course-card > div").first().ownText();
        // get course name
        String name = card.select("div.course-title").text();
        // grab Creator name
        Element maker = card.select("div.creator-info").first();
        String auth = maker.ownText();
        // then form into list
        String[] output = {name, diff, auth};
        return output;
    }
}
