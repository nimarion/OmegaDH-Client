package de.nimarion.osv.protocol.geminiwind;

import de.nimarion.osv.TCPClient;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;
import de.nimarion.osv.protocol.ProtocolConfiguration;


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
    
