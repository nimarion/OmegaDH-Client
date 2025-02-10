package de.nimarion.photofinish.osv.geminiwind.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.geminiwind.event.WindEvent;

public class WindPacket implements Packet {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 20, 83, 48, 48, 2, 8, 32, 32, 88, 2 };

    @Override
    public boolean isPacket(byte[] data) {
        if (data.length < PACKET_IDENTIFIER.length) {
            return false;
        }
        for (int i = 0; i < PACKET_IDENTIFIER.length; i++) {
            if (data[i] != PACKET_IDENTIFIER[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Event handleData(String data) {
        int startIndex = data.lastIndexOf('\u0002') + 1;
        String windString = new String(data.substring(startIndex, startIndex + 4));
        return new WindEvent(Double.parseDouble(windString));
    }

}
