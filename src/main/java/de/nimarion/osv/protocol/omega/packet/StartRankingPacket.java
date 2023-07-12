package de.nimarion.osv.protocol.omega.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.StartRankingEvent;

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
