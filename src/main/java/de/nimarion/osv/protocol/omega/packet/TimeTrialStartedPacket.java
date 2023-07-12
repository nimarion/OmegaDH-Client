package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.RaceStartedEvent;

/**
 * Name: [TimeTrialStart]
 * Group: [RACING]
 * Message: <SOH><DC3>DVIST<STX>%0<EOT>
 * Placeholder 0: RACE_START_TIME
 */
public class TimeTrialStartedPacket extends OmegaPacket {

    public TimeTrialStartedPacket() {
        super("IST");
    }

    @Override
    public Event handleData(String data) {
        return new RaceStartedEvent(data.trim(), true);
    }

}
