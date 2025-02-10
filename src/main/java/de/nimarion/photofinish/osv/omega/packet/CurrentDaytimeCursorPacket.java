package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.CurrentDaytimeCursorEvent;

/**
 * Name: [FOLLOW_TIME_DAYTIME]
 * Group: [JUDGING]
 * Message: <SOH><DC3>DVDFC<STX>%1<EOT>
 * Placeholder 1: FOLLOW_TIME_DAYTIME
 */
public class CurrentDaytimeCursorPacket extends OmegaPacket {

    public CurrentDaytimeCursorPacket() {
        super("DFC");
    }

    @Override
    public Event handleData(String information) {
        return new CurrentDaytimeCursorEvent(information.trim());
    }

}
