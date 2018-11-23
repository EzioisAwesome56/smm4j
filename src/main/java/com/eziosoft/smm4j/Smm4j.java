package com.eziosoft.smm4j;

public class Smm4j {

    public static void main(String[] args){
        // check if arguments where provided
        if (args.length > 0){
            // then parse args
            if (args[0].equals("-cli")){
                System.out.println("Command line mode Selected!");
            }
        }
    }

    // class to get level details
    public static String[] getLevel(String id){
        String[] error = {"error"};
        // is the level id provided blank?
        if (id.equals(null)){
            return error;
        }
    }
}
