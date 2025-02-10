package de.nimarion.photofinish.osv.sirocco;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;

public class WindPacket implements Packet {

    @Override
    public boolean isPacket(byte[] data) {
        throw new UnsupportedOperationException("Unimplemented method 'isPacket'");
    }

    @Override
    public Event handleData(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 2) {
            try {
                float wind = (float) (Math.round(Float.parseFloat(parts[1]) * 10.0) / 10.0);
                return new SiroccoWindEvent(wind);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
