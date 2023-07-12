package de.nimarion.osv.protocol.gemini.packet;

import de.nimarion.osv.protocol.gemini.GeminiPacket;
/**
 * Name: DeleteLine
 * Group: ERASE
 * Message: <SOH><STX><DLE>000%Z<ERL><EOT>
 * Parameter: %Z=11-19 (line number)
 */
public class DeleteLinePacket extends GeminiPacket {
    
}
