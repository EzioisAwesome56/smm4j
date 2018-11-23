package com.eziosoft.smm4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Random {

    public static String GetRandomLevel(){
        Document doc;
        // get the recommended courses page
        try {
            doc = Jsoup.connect(baseurl + id).userAgent("Mozilla/5.0 (Debian Stretch) Gecko/20100101 Firefox/63.0").get();
        } catch (IOException e){
            System.out.println("Error!");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return "error";
        }
    }
}
