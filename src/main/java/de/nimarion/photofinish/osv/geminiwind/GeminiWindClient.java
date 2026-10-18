package de.nimarion.photofinish.osv.geminiwind;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class GeminiWindClient extends TCPClient {

    public GeminiWindClient(String host, int port) {
        super(host, port);
    }

    public GeminiWindClient(ProtocolConfiguration protocolConfiguration) {
        super(protocolConfiguration);
    }

}
    
