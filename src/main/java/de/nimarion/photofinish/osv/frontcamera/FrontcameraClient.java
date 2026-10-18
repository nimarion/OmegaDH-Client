package de.nimarion.photofinish.osv.frontcamera;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;
import de.nimarion.photofinish.osv.frontcamera.packet.DaytimeFrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.packet.EnteredFrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.packet.ExitedFrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.packet.StartedFrontcameraPacket;

public class FrontcameraClient extends TCPClient {

    public FrontcameraClient(String host, int port) {
        super(host, port);
        registerPacket(new DaytimeFrontcameraPacket());
        registerPacket(new EnteredFrontcameraPacket());
        registerPacket(new ExitedFrontcameraPacket());
        registerPacket(new StartedFrontcameraPacket());
    }

    public FrontcameraClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        handleFramedData(packet, data);
    }

}
