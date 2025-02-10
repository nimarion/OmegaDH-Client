package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.FalseStartEvent;

/**
 * Name: [FalseStart]
 * Group: [RACING]
 * Message: <SOH><DC3>DVISF<EOT>
 */
public class FalseStartPacket extends OmegaPacket {

    public FalseStartPacket() {
        super("ISF");
    }

    @Override
    public Event handleData(String data) {
        return new FalseStartEvent();
    }

}
