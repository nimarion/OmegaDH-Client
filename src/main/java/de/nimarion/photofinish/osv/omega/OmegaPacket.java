package de.nimarion.photofinish.osv.omega;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;

public class OmegaPacket implements Packet {

    private final String type;

    public OmegaPacket(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    
    @Override
    public boolean isPacket(byte[] data) {
        byte[] type = new byte[3];
        System.arraycopy(data, 4, type, 0, 3);
        String packetType = new String(type);
        return packetType.equals(getType());
    }

    @Override
    public Event handleData(String data) {
        throw new UnsupportedOperationException("Unimplemented method 'handleData'");
    }

}
