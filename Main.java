import java.net.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        long time1 = System.currentTimeMillis();

        boolean wasV=false;
        boolean wasW=false;
        boolean wasC=false;
        boolean wasE=false;



       String[] urlArray = args[0].split(",");
       String[] patterns = args[1].split(",");

        for (int i = 2; i < args.length ; i++) {

            if(args[i].equals("-v"))
                wasV=true;

            if(args[i].equals("-w"))
                wasW=true;

            if(args[i].equals("-c"))
                wasC=true;

            if(args[i].equals("-e"))
                wasE=true;

        }


        Scrapper scrapper = new Scrapper(patterns,wasW,wasC,wasE);

        for (String url:urlArray)
            Scrapper.scrap(url);


        long time2 = System.currentTimeMillis();

        long diffrence = (time2-time1);
        if(wasV)
            System.out.println("Time spent: "+diffrence+"millis");

    }
}