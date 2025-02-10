package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.RecoverFinishEvent;

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
