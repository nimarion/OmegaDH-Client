package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.RaceStartedEvent;

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
