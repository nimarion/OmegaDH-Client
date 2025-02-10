package de.nimarion.photofinish.osv.geminiwind;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;


public class GeminiWindClient extends TCPClient {

    public GeminiWindClient(String host, int port) {
        super(host, port);
    }

    public GeminiWindClient(ProtocolConfiguration protocolConfiguration) {
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
    
