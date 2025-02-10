package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.CurrentRacetimeCursorEvent;

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
