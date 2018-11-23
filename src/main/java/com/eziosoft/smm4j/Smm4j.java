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
                Cli.cli(args);
            }
        }
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
        String auth = maker.text();
        // get image link
        String imglink = card.select("img.course-image").attr("src");
        // grab fullimage also
        String fullimglink = card.select("img.course-image-full").attr("src");
        // then form into list
        return new String[]{name, diff, auth, imglink, fullimglink, id, baseurl + id};
    }
}
