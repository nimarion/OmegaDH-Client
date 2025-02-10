package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.ResultsOfficialEvent;

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
