package de.nimarion.photofinish.osv.gemini.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.gemini.GeminiPacket;
import de.nimarion.photofinish.osv.gemini.event.AlphabeticLineEvent;

/**
 * Name: Title, AlphaLine
 * Group: STARTLIST, RACING, RESULT, TEST_ALPHANUM
 * Message: <SOH><DC3>aA<STX><DLE>00%Z%1<EOT>
 * Parameter: %Z=11-19 (line number), %1=text
 */
public class AlphaLinePacket extends GeminiPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 19, 97, 65, 2, 16, 48, 48 };

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
        int line = Integer.parseInt(data.substring(9, 10)) + 1;
        String text = data.substring(10, data.length() - 2).trim();
        if(text.isEmpty()) {
            return null;
        }
        return new AlphabeticLineEvent(line, text);
    }

}
