package de.nimarion.photofinish.osv.gemini;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class GeminiClient extends TCPClient {

    public GeminiClient(String host, int port) {
        super(host, port);
    }

    public GeminiClient(ProtocolConfiguration protocolConfiguration) {
        super(protocolConfiguration);
    }

}
