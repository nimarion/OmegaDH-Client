package de.nimarion.photofinish.osv.gemini;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class GeminiClient extends TCPClient {

    public GeminiClient(String host, int port) {
        super(host, port);
    }

    public GeminiClient(ProtocolConfiguration protocolConfiguration) {
        super(protocolConfiguration);
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        Event event = packet.handleData(new String(data));
        if (event != null) {
            handleEvent(event);
        }
    }

}
