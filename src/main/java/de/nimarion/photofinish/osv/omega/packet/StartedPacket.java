package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.RaceStartedEvent;

/**
 * Name: [Started]
 * Group: [RACING]
 * Message: <SOH><DC3>DVISV<STX>%0<EOT>
 * Placeholder 0: RACE_START_TIME
 */
public class StartedPacket extends OmegaPacket {

    public StartedPacket() {
        super("ISV");
    }

    @Override
    public Event handleData(String data) {
        return new RaceStartedEvent(data.trim());
    }

}
