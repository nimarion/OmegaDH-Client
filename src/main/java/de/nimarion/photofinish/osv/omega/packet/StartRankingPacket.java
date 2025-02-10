package de.nimarion.photofinish.osv.omega.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.StartRankingEvent;

/**
 * Name: [StartRanking]
 * Group: [RESULT]
 * Message: <SOH><DC3>DVRDC<STX>%0<EOT>
 * Placeholder 0: CLAS_NB_COMPETITORS
 */
public class StartRankingPacket extends OmegaPacket {

    public StartRankingPacket() {
        super("RDC");
    }

    @Override
    public Event handleData(String information) {
        return new StartRankingEvent(Integer.parseInt(information.trim()));
    }

}
