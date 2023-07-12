package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.RecoverFinishEvent;

/**
 * Name: [RecoverFinish]
 * Group: [RACING]
 * Message: <SOH><DC3>DVIFF<EOT>
 */
public class RecoverFinishPacket extends OmegaPacket {

    public RecoverFinishPacket() {
        super("IFF");
    }

    @Override
    public Event handleData(String data) {
       return new RecoverFinishEvent();
    }

}
