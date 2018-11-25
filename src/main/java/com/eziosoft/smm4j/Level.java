package com.eziosoft.smm4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.util.Iterator;
import java.util.List;

import static com.eziosoft.smm4j.Smm4j.baseurl;

public class Level {

    public String id;
    public String name;
    public String difficulty;
    public int liked;
    public int played;
    public int shared;
    public String author;
    public String imgURL;
    public String fullImgURL;
    public int clears;
    public int attempts;

    private GameSkin skin;
    private String date;

    private Level() {}

    public static Level getLevel(String id) throws NullPointerException {
        Document doc;
        //Is the level id provided blank? If so, throw NullPointer.
        if (id.length() == 0) {
            throw new NullPointerException("No Level ID");
        }

        //Use jsoup to grab the document, if ratelimited, throw NullPointer.
        String html = Util.getDocument(baseurl + id);
        if (html.equals("403")) {
            System.out.println("Nintendo is rate limiting you!");
            throw new NullPointerException("Ratelimited, no level returned.");
        }
        //We weren't ratelimited, so we got SOMETHING. Let's investigate.
        doc = Jsoup.parse(html);

        //Did we get an error? If so, throw NullPointer.
        if (doc.select("div.error-description").text().equals("The page could not be found.")) {
            throw new NullPointerException("Level not found");
        }

        //Error wasn't thrown, MAKE A NEW LEVEL OBJECT!
        Level level = new Level();

        //Set level ID.
        level.id = id;

        //The following rips only the course card, which contains all the data we need anyway.
        Elements card = doc.select("div.course-card");

        //Set difficulty
        level.difficulty = doc.select("div.course-card > div").first().ownText();
        //Set course name
        level.name = card.select("div.course-title").text();
        //Set Creator name
        level.author = card.select("div.creator-info").first().text();
        //Set image link
        level.imgURL = card.select("img.course-image").attr("src");
        //Set full image also
        level.fullImgURL = card.select("img.course-image-full").attr("src");
        //Set date submitted
        level.date = card.select("div.course-image-wrapper")
                .select("div.created_at")
                .text();

        //And now, here's where things get complicated.

        /*
        Let's get the game skin, all four skins have a different class name, so we'll have to deal with it once we have it.
        The main thing, is that the class that defines the skin looks something like this:
        "gameskin bg-image common_gs_sb3" (<- SMB3 skin)
        We need to split the string by spaces, get the second value in the array, split it again by underscores, and then get the second value once again.
        Needless to say, this is a slightly complicated process, and it's gonna be squished into a one-liner.
        What we should be left with is something like this:
        "sb3" (<- SMB3 skin)
        This is what we'll use to determine what game we're looking at.
         */
        String gameSkin = card.select("div.course-image-wrapper")
                .select("div.gameskin-wrapper")
                .first()
                .child(0)
                .className()
                .split(" ")[2]
                .split("_")[2];

        //Now that we have the game skin, let's figure out which game specifically it is.
        switch (gameSkin) {
            case "sb":
                level.skin = GameSkin.SMB1;
                break;
            case "sb3":
                level.skin = GameSkin.SMB3;
                break;
            case "sw":
                level.skin = GameSkin.SMW;
                break;
            case "sbu":
                level.skin = GameSkin.NSMBU;
                break;
        }

        /*
        Great, now we have the game skin. But we're still missing some things.
        The following makes use of a method in Util to parse characters, Nintendo decided to make things complicated and use images for some of these details instead of plain text.
        Thanks, Nintendo.
         */
        Elements stats = card.select("div.course-stats-wrapper");
        level.liked = Util.parseTypography(stats.select("div.liked-count").first().children());
        level.played = Util.parseTypography(stats.select("div.played-count").first().children());
        level.shared = Util.parseTypography(stats.select("div.shared-count").first().children());

        //Temporary variable because clears/attempts is combined in one "string".
        Elements temp = card.select("div.tried-count").first().children();

        //We also need to find the "slash" character, unfortunately, there's no easy way to do this, so we've gotta do things the complicated way.
        Element slash = null;
        for (Element e : temp) {
            if (e.className().contains("slash")) {
                slash = e;
                break;
            }
        }
        //Now that we found slash, let's set clears by using a sublist from 0, to the position of slash, and parsing it.
        level.clears = Util.parseTypography(temp.subList(0, temp.indexOf(slash)));
        //We also need to make a sublist from the position of slash + 1, to the end of the list, and parse that for attempts.
        level.attempts = Util.parseTypography(temp.subList(temp.indexOf(slash) + 1, temp.size()));

        //That's it! We have everything we need! Let's returned the now completed level object!
        return level;
    }


        private enum GameSkin {

            SMB1("Super Mario Bros."), SMB3("Super Mario Bros. 3"), SMW("Super Mario World"), NSMBU("New Super Mario Bros. U");

            private String fullName;

            GameSkin(String fullName) {
                this.fullName = fullName;
            }

            public String getFullName() {
                return fullName;
            }

            public String getShortName() {
                return this.name();
            }

        }
}
