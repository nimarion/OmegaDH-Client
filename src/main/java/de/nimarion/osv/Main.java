package de.nimarion.osv;

import java.io.IOException;

import com.google.gson.GsonBuilder;

import de.nimarion.osv.module.scoreboard.ScoreboardEventHandler;
import de.nimarion.osv.protocol.frontcamera.FrontcameraClient;
import de.nimarion.osv.protocol.gemini.GeminiClient;
import de.nimarion.osv.protocol.geminiwind.GeminiWindClient;
import de.nimarion.osv.protocol.omega.OmegaClient;

public class Main {

    public Main(Configuration configuration) {
        if (configuration.getGeminiConfiguration() != null) {
            TCPClient geminiClient = new GeminiClient(configuration.getGeminiConfiguration());
            try {
                geminiClient.addEventHandler(
                        new ScoreboardEventHandler(configuration.getBroadcastAddress(), configuration.getNetworkBindAddress()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            geminiClient.start();
        }
        if (configuration.getGeminiWindConfiguration() != null) {
            TCPClient geminiWindClient = new GeminiWindClient(configuration.getGeminiWindConfiguration());
            geminiWindClient.start();
        }
        if (configuration.getOmegaConfiguration() != null) {
            TCPClient omegaClient = new OmegaClient(configuration.getOmegaConfiguration());
            omegaClient.start();
        }
        if (configuration.getFrontcameraConfiguration() != null) {
            TCPClient frontcameraclient = new FrontcameraClient(configuration.getFrontcameraConfiguration());
            frontcameraclient.start();
        }
    }

    public static void main(String... args) throws Exception {
        Configuration configuration = Configuration.getConfiguration();
        if (configuration == null) {
            System.out.println("configuration.json not found");
            return;
        }
        new Main(configuration);
    }

}
