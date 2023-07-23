package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.NetTimeEvent;

/**
 * Name: [NetTimeA]
 * Group: [RACING]
 * Message: <SOH><DC3>DVIFV<STX>%0<EOT>
 * Placeholder 0: RACE_NET_TIME
 */
public class NetTimePacket extends OmegaPacket{

    public NetTimePacket() {
        super("IFV");
    }

    @Override
    public Event handleData(String data) {
        return new NetTimeEvent(data.trim());
    }
 
}
