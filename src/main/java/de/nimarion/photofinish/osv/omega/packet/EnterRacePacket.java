package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.EnterRaceEvent;

/**
 * Name: [EnterRace, StartResults]
 * Group: [RACING, RESULT]
 * Message: <SOH><DC3>DVSNR<STX>%0<EOT>
 * Placeholder 0: RACE_ID
 */
public class EnterRacePacket extends OmegaPacket {

    public EnterRacePacket() {
        super("SNR");
    }

    @Override
    public Event handleData(String information) {
        return new EnterRaceEvent(information.trim());
    }

}
