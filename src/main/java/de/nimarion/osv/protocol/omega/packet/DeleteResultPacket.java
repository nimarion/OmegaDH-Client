package de.nimarion.osv.protocol.omega.packet;

import java.util.Arrays;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.DeleteResultEvent;

/**
 * Name: [RemoveResult]
 * Group: [JUDGING, RESULT]
 * Message: <SOH><DC3>DVRCS<STX>%1%2<EOT>
 * Placeholder 1: BIB
 * Placeholder 2: LANE
 */
public class DeleteResultPacket extends OmegaPacket {

    public DeleteResultPacket() {
        super("RCS");
    }
    
    @Override
    public Event handleData(String asciiString) {
        char data[] = asciiString.toCharArray();
        int  bib = Integer.parseInt(new String(Arrays.copyOfRange(data, 0, 5)).trim());
        int lane = Integer.parseInt(new String(Arrays.copyOfRange(data, 5, 7)).trim());
        return new DeleteResultEvent(bib, lane);
    }

}
