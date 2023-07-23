package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class StartRankingEvent extends OmegaEvent {

    private int judged_competitors; 

    public StartRankingEvent(int judged_competitors) {
        super("START_RANKING");
        this.judged_competitors = judged_competitors;
    }

    public int getJudgedCompetitors() {
        return judged_competitors;
    }
    
}
