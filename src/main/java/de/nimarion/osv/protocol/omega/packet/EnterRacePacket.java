package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.EnterRaceEvent;

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
