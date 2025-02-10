package de.nimarion.photofinish.osv.gemini.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.gemini.GeminiPacket;
import de.nimarion.photofinish.osv.gemini.event.LuminosityEvent;

/**
 * Name: LuminosityLevel
 * Group: COMMANDS
 * Message: <SOH><DC4>L%0<EOT>
 * Paramater: %0=0-4
 */
public class LuminosityPacket extends GeminiPacket {

    private static final byte[] PACKET_IDENTIFIER = new byte[] { 1, 20, 76 };

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
        int lumiosity = Character.getNumericValue(data.charAt(3));
        return new LuminosityEvent(lumiosity);
    }

}
