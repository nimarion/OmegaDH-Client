package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.common.result.ResultEndEvent;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;

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
        return new ResultEndEvent();
    }

}
