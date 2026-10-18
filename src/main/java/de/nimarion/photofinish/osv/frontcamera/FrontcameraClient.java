package de.nimarion.photofinish.osv.frontcamera;

import de.nimarion.photofinish.TCPClient;
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
        handleFramedData(packet, data);
    }

}
