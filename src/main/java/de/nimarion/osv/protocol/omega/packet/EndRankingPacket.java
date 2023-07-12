package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.EndRankingEvent;

/**
 * Name: [EndRanking]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVRFC<EOT>
 */
public class EndRankingPacket extends OmegaPacket {

    public EndRankingPacket() {
        super("RFC");
    }

    @Override
    public Event handleData(String data) {
        return new EndRankingEvent();
    }

}
