package de.nimarion.osv.protocol.omega.packet;

import java.util.Arrays;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.ResultHundredsEvent;

/**
 * Name: [Result]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVRCC<STX>%0%1%2%3<EOT> 
 * Placeholder 0: RANK
 * Placeholder 1: BIB
 * Placeholder 2: LANE
 * Placeholder 3: TIME (HUNDREDS), DNS, No Time, DNF, DQ, USER1, USER2, USER3
 */
public class ResultHundredsPacket extends OmegaPacket {

    public ResultHundredsPacket() {
        super("RCC");
    }

    @Override
    public Event handleData(String dataString) {
        char[] data = dataString.toCharArray();
        String rankString = new String(Arrays.copyOfRange(data, 0, 3)).trim();
        Integer rank = rankString.isEmpty() ? null : Integer.parseInt(rankString);
        String bib = new String(Arrays.copyOfRange(data, 3, 8)).trim();
        int lane = Integer.parseInt(new String(Arrays.copyOfRange(data, 8, 10)).trim());
        String time = new String(Arrays.copyOfRange(data, 10, data.length)).trim();
        return new ResultHundredsEvent(rank, bib, lane, time);
    }

}
