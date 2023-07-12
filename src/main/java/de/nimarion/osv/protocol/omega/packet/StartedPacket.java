package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.RaceStartedEvent;

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
