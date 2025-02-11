package de.nimarion.photofinish.osv.omega.packet;

import java.util.Arrays;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.ResultThousandsEvent;

/**
 * Name: [Result]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVRCF<STX>%0%1%2%4<EOT>
 * Placeholder 0: RANK
 * Placeholder 1: BIB
 * Placeholder 2: LANE
 * Placeholder 4: TIME (THOUSANDS)
 */
public class ResultThousandsPacket extends OmegaPacket {

    public ResultThousandsPacket() {
        super("RCF");
    }

    @Override
    public Event handleData(String dataString) {
        char data[] = dataString.toCharArray();
        int rank = Integer.parseInt(new String(Arrays.copyOfRange(data, 0, 3)).trim());
        int bib = Integer.parseInt(new String(Arrays.copyOfRange(data, 3, 8)).trim());
        int lane = Integer.parseInt(new String(Arrays.copyOfRange(data, 8, 10)).trim());
        String time = new String(Arrays.copyOfRange(data, 10, data.length))
                .split("\\.")[1].trim();

        return new ResultThousandsEvent(rank, bib, lane, time);
    }

}
