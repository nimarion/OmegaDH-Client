package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.ResultsOfficialEvent;

/**
 * Name: [Offical]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVRFJ<STX>%0<EOT>
 * Placeholder 0: RACE_ID
 */
public class ResultsOfficalPacket extends OmegaPacket {

    public ResultsOfficalPacket() {
        super("RFJ");
    }

    @Override
    public Event handleData(String data) {
        return new ResultsOfficialEvent(data.trim());
    }

}
