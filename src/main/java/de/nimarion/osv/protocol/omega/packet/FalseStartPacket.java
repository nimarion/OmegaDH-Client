package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.FalseStartEvent;

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
