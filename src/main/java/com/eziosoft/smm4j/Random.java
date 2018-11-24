package com.eziosoft.smm4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Random {

    public static String GetRandomLevel(){
        String html = Util.getDocument("re");
        if (html.equals("403")){
            System.out.println("Nintendo is rate limiting you!");
            return "error";
        }
        return "a";
    }
}
