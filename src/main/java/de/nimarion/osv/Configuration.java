package de.nimarion.osv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.google.gson.Gson;

import de.nimarion.osv.protocol.ProtocolConfiguration;

public class Configuration {    

    private String networkBindAddress;
    private String broadcastAddress;
    private ProtocolConfiguration frontcamera;
    private ProtocolConfiguration omega;
    private ProtocolConfiguration gemini;
    private ProtocolConfiguration geminiWind;
    private transient static Configuration configuration;

    public Configuration() {
        this.networkBindAddress = "12";
        this.frontcamera = new ProtocolConfiguration();
        this.omega = new ProtocolConfiguration();
        this.gemini = new ProtocolConfiguration();
        this.geminiWind = new ProtocolConfiguration();
    }

    public ProtocolConfiguration getFrontcameraConfiguration() {
        if(frontcamera != null && !frontcamera.isEnabled()){
            return null;
        }
        return frontcamera;
    }

    public ProtocolConfiguration getOmegaConfiguration() {
        if(omega != null && !omega.isEnabled()){
            return null;
        }
        return omega;
    }

    public ProtocolConfiguration getGeminiConfiguration() {
        if(gemini != null && !gemini.isEnabled()){
            return null;
        }
        return gemini;
    }

    public ProtocolConfiguration getGeminiWindConfiguration() {
        if(geminiWind != null && !geminiWind.isEnabled()){
            return null;
        }
        return geminiWind;
    }

    public String getNetworkBindAddress() {
        return networkBindAddress;
    }

    public String getBroadcastAddress() {
        return broadcastAddress;
    }

    public static Configuration getConfiguration() throws FileNotFoundException {
        if (configuration == null) {
            configuration = new Gson().fromJson(new FileReader(new File("configuration.json")), Configuration.class);
        }
        return configuration;
    }

}
