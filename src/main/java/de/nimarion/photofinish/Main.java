package de.nimarion.photofinish;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxClient;
import de.nimarion.photofinish.osv.startlist.StartlistParser;

public class Main {

    public static void main(String... args){
        StartlistParser parser = new StartlistParser("C:/temp/startlist.csv");
        parser.getStartlists().forEach(startlist -> {
            System.out.println(startlist.getTitle() + " - " + startlist.getStartTime());
        });
        FinishLynxClient geminiClient = new FinishLynxClient("127.0.0.1", 9998);
        geminiClient.start();
    }
    
}
