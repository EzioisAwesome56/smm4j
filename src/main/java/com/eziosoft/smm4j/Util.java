package com.eziosoft.smm4j;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Util {

    static String getDocument(String url){
        Document doc;
        // get the recommended courses page
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/6.9 (Floatzel LINUX) Firefox/420.0").get();
        } catch (HttpStatusException e){
            return Integer.toString(e.getStatusCode());
        } catch (IOException e){
            e.printStackTrace();
            return "error";
        }
        // spit the document out as a string
        return doc.html();
    }

    public static String makeUrl(String id){
        return Smm4j.baseurl + id;
    }

    public static String GetRandomLevelID(){
        String html = getDocument("https://supermariomakerbookmark.nintendo.net/pickup");
        if (html.equals("403")){
            System.out.println("Nintendo is rate limiting you!");
            return "error";
        }
        //The following is a huge one-liner that parses the site, gets the URL, and gets the level ID from the URL.
        return Jsoup.parse(html)
                .select("div.two-column-wrapper")
                .select("div:first-child")
                .select("div.course-detail-wrapper")
                .select("a.course-detail")
                .attr("href")
                .split("/")[2];
    }

    public static int parseTypography(Elements elements) {
        //Temporary string to throw all the numbers into.
        final String[] temp = {""};
        elements.forEach(e -> {
            if (e.className().startsWith("typography")) {
                temp[0] += e.className().replace("typography typography-", "");
            }
        });
        return Integer.parseInt(temp[0]);
    }

    //Overloaded method because List objects.
    public static int parseTypography(List<Element> elements) {
        //Temporary string to throw all the numbers into.
        final String[] temp = {""};
        elements.forEach(e -> {
            if (e.className().startsWith("typography")) {
                temp[0] += e.className().replace("typography typography-", "");
            }
        });
        return Integer.parseInt(temp[0]);
    }
}
