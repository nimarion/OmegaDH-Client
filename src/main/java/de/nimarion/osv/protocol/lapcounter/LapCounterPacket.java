package de.nimarion.osv.protocol.lapcounter;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

public class LapCounterPacket implements Packet {

    @Override
    public boolean isPacket(byte[] data) {
        return false;
    }

    @Override
    public Event handleData(String data) {
        throw new UnsupportedOperationException("Unimplemented method 'handleData'");
    }
    
}
