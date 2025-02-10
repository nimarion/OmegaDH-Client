package de.nimarion.photofinish.osv.gemini.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.gemini.GeminiPacket;
import de.nimarion.photofinish.osv.gemini.event.ClearDisplayEvent;

/**
 * Name: Clear
 * Group: CLEAR
 * Message: <SOH><STX><ERP><EOT>
 */
public class ClearDisplayPacket extends GeminiPacket{

    @Override
    public boolean isPacket(byte[] data) {
        byte[] packetIdentifier = new byte[] { 1 , 2 , 12 , 4 , 0 };
        if(data.length != packetIdentifier.length) {
            return false;
        }
        for (int i = 0; i < packetIdentifier.length; i++) {
            if (data[i] != packetIdentifier[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Event handleData(String data) {
       return new ClearDisplayEvent();
    }   
    
}
