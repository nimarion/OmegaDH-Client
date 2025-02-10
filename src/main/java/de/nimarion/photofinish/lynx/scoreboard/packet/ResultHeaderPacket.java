package de.nimarion.photofinish.lynx.scoreboard.packet;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxPacket;
import de.nimarion.photofinish.lynx.scoreboard.ResultStartEvent;
import de.nimarion.photofinish.osv.Event;

public class ResultHeaderPacket extends FinishLynxPacket {

    private static final byte[] HEADER_PACKET_IDENTIFIER = new byte[] { 1, 76, 72, 2 };
    private static final byte[] STARTLIST_PACKET_IDENTIFIER = new byte[] { 1, 83, 72, 2};

    @Override
    public boolean isPacket(byte[] data) {
        if (data.length < HEADER_PACKET_IDENTIFIER.length && data.length < STARTLIST_PACKET_IDENTIFIER.length) {
            return false;
        }
        for (int i = 0; i < HEADER_PACKET_IDENTIFIER.length; i++) {
            if (data[i] != HEADER_PACKET_IDENTIFIER[i] && data[i] != STARTLIST_PACKET_IDENTIFIER[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Event handleData(String data) {
        int startIndex = data.indexOf('\u0002');
        int lastIndex = data.lastIndexOf('\u0004');
        String[] splitData = data.substring(startIndex, lastIndex).trim().split("\\|(?!\\s)");
        String eventName = getOrDefault(splitData, 1, "");
        String wind = getOrDefault(splitData, 2, "");
        int eventNumber = getOrDefault(splitData, 3, -1);
        int roundNumber = getOrDefault(splitData, 4, -1);
        int heatNumber =  getOrDefault(splitData, 5, -1);
        int numberOfParticipants = getOrDefault(splitData, 7, -1);
        return new ResultStartEvent(eventName, wind, eventNumber, roundNumber, heatNumber, numberOfParticipants);
    }

    
    private String getOrDefault(String[] data, int index, String defaultValue) {
        if (data.length > index) {
            return data[index].trim();
        }
        return defaultValue;
    }

    private int getOrDefault(String[] data, int index, int defaultValue) {
        if (data.length > index) {
            try {
                return Integer.parseInt(data[index].trim());
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return defaultValue;
    }
}
