package de.nimarion.osv.protocol.frontcamera;

import de.nimarion.osv.TCPClient;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

public class FrontcameraClient extends TCPClient {

    public FrontcameraClient(String host, int port) {
        super(host, port);
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
