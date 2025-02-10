package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.EndRankingEvent;

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
