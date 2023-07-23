package de.nimarion.osv.protocol.geminiwind;

import de.nimarion.osv.TCPClient;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;


public class GeminiWindClient extends TCPClient {

    public GeminiWindClient(String host, int port) {
        super(host, port);
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        Event event = packet.handleData(new String(data));
        if (event != null) {
            handleEvent(event);
        }
    }

}
    
