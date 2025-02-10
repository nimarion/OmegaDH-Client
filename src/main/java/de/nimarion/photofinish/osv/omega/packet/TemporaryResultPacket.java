package de.nimarion.photofinish.osv.omega.packet;

import java.util.Arrays;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.TemporaryResultEvent;

/**
 * Name: [TempResult, TempResult_DNS, TempResult_DNF, TempResult_DQ, TempResult_NT, TempResult_USER1, ResultOnLine]
 * Group: [JUDGING, RESULT]
 * Message: <SOH><DC3>DVRCV<STX>%1%2%3<EOT>
 * Placeholder 1: BIB
 * Placeholder 2: LANE
 * Placeholder 3: TIME, DNS, DNF, DQ, NT, USER1
 */
// Use case? Manual Start without thousands packet?
public class TemporaryResultPacket extends OmegaPacket {

    public TemporaryResultPacket() {
        super("RCV");
    }

    @Override
    public Event handleData(String dataString) {
        char[] data = dataString.toCharArray();
        int bib = Integer.parseInt(new String(Arrays.copyOfRange(data, 0, 5)).trim());
        int lane = Integer.parseInt(new String(Arrays.copyOfRange(data, 5, 7)).trim());
        String time = new String(Arrays.copyOfRange(data, 7, 19)).trim();
        return new TemporaryResultEvent(bib, lane, time);
    }

}
