package de.nimarion.photofinish.lynx.scoreboard;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;

public class FinishLynxPacket implements Packet {

    @Override
    public boolean isPacket(byte[] data) {
        return false;
    }

    @Override
    public Event handleData(String data) {
        throw new UnsupportedOperationException("Unimplemented method 'handleData'");
    }

}
