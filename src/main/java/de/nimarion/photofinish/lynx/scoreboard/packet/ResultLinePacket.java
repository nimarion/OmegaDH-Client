package de.nimarion.photofinish.lynx.scoreboard.packet;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxPacket;
import de.nimarion.photofinish.lynx.scoreboard.event.ResultLineEvent;
import de.nimarion.photofinish.osv.Event;

public class ResultLinePacket extends FinishLynxPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 76, 82, 2 };

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
        int startIndex = data.indexOf('\u0002');
        int lastIndex = data.lastIndexOf('\u0004');
        String[] splitData = data.substring(startIndex, lastIndex).trim().split("\\|");
        Integer place = getOrDefaultInt(splitData, 0, null);
        Integer lane = getOrDefaultInt(splitData, 1, null);
        Integer id = getOrDefaultInt(splitData, 2, null);
        String name = getOrDefault(splitData, 3, "");
        String affilation = getOrDefault(splitData, 4, "");
        String time = getOrDefault(splitData, 5, "");
        String reactionTime = getOrDefault(splitData, 11, "");
        return new ResultLineEvent(place, lane, id, name, affilation, time, reactionTime);
    }

    private String getOrDefault(String[] data, int index, String defaultValue) {
        if (data.length > index) {
            return data[index].trim();
        }
        return defaultValue;
    }

    private Integer getOrDefaultInt(String[] data, int index, Integer defaultValue) {
        if (data.length > index) {
            try {
                return Integer.parseInt(data[index].trim());
            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
