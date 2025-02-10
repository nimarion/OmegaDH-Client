package de.nimarion.photofinish.lynx.scoreboard.packet;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxPacket;
import de.nimarion.photofinish.lynx.scoreboard.ResultEndEvent;
import de.nimarion.photofinish.osv.Event;

public class ResultTrailerPacket extends FinishLynxPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 76, 69, 4 };

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
        return new ResultEndEvent();
    }
}
