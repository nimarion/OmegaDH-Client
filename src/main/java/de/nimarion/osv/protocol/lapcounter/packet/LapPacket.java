package de.nimarion.osv.protocol.lapcounter.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.lapcounter.LapCounterPacket;
import de.nimarion.osv.protocol.lapcounter.event.LapEvent;

public class LapPacket extends LapCounterPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 83, 48, 83,76, 67, 2 };

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
        int startIndex = data.lastIndexOf('\u0002');
        int lastIndex = data.lastIndexOf('\u0004');
        int lap = Integer.valueOf(data.substring(startIndex, lastIndex).trim());
        return new LapEvent(lap);
    }
    
}


