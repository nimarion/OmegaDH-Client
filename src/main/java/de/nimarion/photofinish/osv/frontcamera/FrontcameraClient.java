package de.nimarion.photofinish.osv.frontcamera;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class FrontcameraClient extends TCPClient {

    public FrontcameraClient(String host, int port) {
        super(host, port);
    }

    public FrontcameraClient(ProtocolConfiguration protocolConfiguration) {
        super(protocolConfiguration);
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        Event event;
        if (data.length <= 9) {
            event = packet.handleData(null);
        } else {
            byte[] information = new byte[data.length - 10];
            System.arraycopy(data, 8, information, 0, data.length - 10);
            event = packet.handleData(new String(information));
        }
        if (event != null) {
            handleEvent(event);
        }
    }
    
}
