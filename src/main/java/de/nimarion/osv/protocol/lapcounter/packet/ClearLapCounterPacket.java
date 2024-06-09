package de.nimarion.osv.protocol.lapcounter.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.lapcounter.LapCounterPacket;
import de.nimarion.osv.protocol.lapcounter.event.ClearLapCounterEvent;

public class ClearLapCounterPacket extends LapCounterPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 83, 48, 83, 68, 48, 4, 0 };

    @Override
    public boolean isPacket(byte[] data) {
        if (data.length != PACKET_IDENTIFIER.length) {
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
        return new ClearLapCounterEvent();
    }

}
