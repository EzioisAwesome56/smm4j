package com.eziosoft.smm4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Random {

    public static String GetRandomLevel(){
        String html = Util.getDocument("https://supermariomakerbookmark.nintendo.net/pickup");
        if (html.equals("403")){
            System.out.println("Nintendo is rate limiting you!");
            return "error";
        }
        Document doc = Jsoup.parse(html);
        Elements wrapper =  doc.select("div.two-column-wrapper");
        Elements card = wrapper.select("div:first-child");
        Elements detail = card.select("div.course-detail-wrapper");
        String relurl = detail.select("a.course-detail").attr("href");
        String[] splitid = relurl.split("/");
        return splitid[2];
    }
}
