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
        if (id.length() == 0){
            return error;
        }
        // use jsoup to grab the document
        String html = Util.getDocument(baseurl + id);
        if (html.equals("403")){
            System.out.println("Nintendo is rate limiting you!");
            return new String[]{"error"};
        }
        doc = Jsoup.parse(html);
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
