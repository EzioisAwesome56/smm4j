package com.eziosoft.smm4j;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Util {

    public static String getDocument(String url){
        Document doc;
        // get the recommended courses page
        try {
            doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Debian Stretch) Firefox/420.0").get();
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
}
