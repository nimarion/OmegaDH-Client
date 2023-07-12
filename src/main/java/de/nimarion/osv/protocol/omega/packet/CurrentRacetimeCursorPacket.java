package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.CurrentRacetimeCursorEvent;

/**
 * Name: [FOLLOW_TIME_RACETIME]
 * Group: [JUDGING]
 * Message: <SOH><DC3>DVTFC<STX>%1<EOT>
 * Placeholder 1: FOLLOW_TIME_RACETIME
 */
public class CurrentRacetimeCursorPacket extends OmegaPacket {

    public CurrentRacetimeCursorPacket() {
        super("TFC");
    }

    @Override
    public Event handleData(String information) {
        return new CurrentRacetimeCursorEvent(information.trim());
    }

}
