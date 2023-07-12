package de.nimarion.osv.protocol.frontcamera;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

public class FrontcameraPacket implements Packet {

    private final String type;

    public FrontcameraPacket(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean isPacket(byte[] data) {
        byte[] type = new byte[3];
        System.arraycopy(data, 2, type, 0, 3);
        String packetType = new String(type);
        return packetType.equals(getType());
    }

    @Override
    public Event handleData(String data) {
        throw new UnsupportedOperationException("Unimplemented method 'handleData'");
    }
    
}
