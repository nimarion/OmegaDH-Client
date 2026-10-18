package de.nimarion.photofinish.osv.geminiwind;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.ProtocolConfiguration;
import de.nimarion.photofinish.osv.geminiwind.packet.WindPacket;

public class GeminiWindClient extends TCPClient {

    public GeminiWindClient(String host, int port) {
        super(host, port);
        registerPacket(new WindPacket());
    }

    public GeminiWindClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

}
    
