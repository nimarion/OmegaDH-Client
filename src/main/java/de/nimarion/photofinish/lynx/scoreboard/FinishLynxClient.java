package de.nimarion.photofinish.lynx.scoreboard;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class FinishLynxClient extends TCPClient {

    public FinishLynxClient(String host, int port) {
        super(host, port);
    }

    public FinishLynxClient(ProtocolConfiguration protocolConfiguration) {
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
