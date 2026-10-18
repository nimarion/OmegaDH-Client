package de.nimarion.photofinish.osv;

public class ProtocolConfiguration {

    private String ip;
    private int port;
    private boolean enabled;

    public ProtocolConfiguration() {

    }

    public ProtocolConfiguration(String ip, int port, boolean enabled) {
        this.ip = ip;
        this.port = port;
        this.enabled = enabled;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
