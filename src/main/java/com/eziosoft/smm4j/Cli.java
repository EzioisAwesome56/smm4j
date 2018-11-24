package com.eziosoft.smm4j;

class Cli {

    static void cli(String[] args){
        boolean rand = false;
        String extid = "";
        if (args[1].equals("-rand")){
            extid = Random.GetRandomLevel();
            rand = true;
        }
        // check if its blank
        String id;
        try{
            id = args[1];
        } catch (NullPointerException e){
            System.out.println("Error: you gave no level id!");
            return;
        }
        if (rand){
            id = extid;
        }
        // pass id to getlevel
        String[] a = Smm4j.getLevel(id);
        // error?
        if (a[0].equals("error")){
            System.out.println("An error has occured!");
            return;
        } else if (a[0].equals("403")){
            System.out.println("Error: Nintendo is rate limiting you!");
            return;
        }
        // print all the stuff
        System.out.println("URL: "+a[6]);
        System.out.println("ID: "+a[5]);
        System.out.println("NAME: "+a[0]);
        System.out.println("DIFF: "+a[1]);
        System.out.println("AUTHOR: "+a[2]);
        System.out.println("IMG LINK: "+a[3]);
        System.out.println("FULLIMG LINK: "+a[4]);
    }
}
