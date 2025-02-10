package de.nimarion.photofinish.osv.omega.packet;

import java.util.Arrays;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.ResultIntermediateEvent;

/**
 * Name: [ResultIntermediate]
 * Group: [RACING, JUDGING, RESULT]
 * Message: <SOH><DC3>DVRTC<STX>%0%1%2%3%4<EOT>
 * Placeholder 0: LAP
 * Placeholder 1: RANK
 * Placeholder 2: BIB
 * Placeholder 3: LANE
 * Placeholder 4: TIME, DNS, DNF, DQ, NT, USER1
 */
public class ResultIntermediatePacket extends OmegaPacket {

    public ResultIntermediatePacket() {
        super("RTC");
    }

    @Override
    public boolean isPacket(byte[] data) {
        if(!super.isPacket(data)){
            return false;
        }
        if(data.length >= 11 && data[11] == 48){
            return false;
        }
        return true;
    }

    @Override
    public Event handleData(String dataString) {
        char[] data = dataString.toCharArray();
        int lap = Integer.parseInt(new String(Arrays.copyOfRange(data, 0, 4)).trim());
        int rank = Integer.parseInt(new String(Arrays.copyOfRange(data, 4, 7)).trim());
        int bib = Integer.parseInt(new String(Arrays.copyOfRange(data, 7, 12)).trim());
        int lane = Integer.parseInt(new String(Arrays.copyOfRange(data, 12, 14)).trim());
        String time = new String(Arrays.copyOfRange(data, 14, data.length)).trim();
        return new ResultIntermediateEvent(lap, rank, bib, lane, time);
    }

}
