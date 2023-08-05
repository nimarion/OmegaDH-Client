package de.nimarion.osv.protocol;

public class ProtocolConfiguration {

    private String ip;
    private int port;
    private boolean enabled;

    public ProtocolConfiguration() {

    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
