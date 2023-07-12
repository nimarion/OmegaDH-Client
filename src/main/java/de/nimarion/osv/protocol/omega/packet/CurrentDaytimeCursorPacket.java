package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.CurrentDaytimeCursorEvent;

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
