package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class StartRankingEvent extends Event {

    private int judged_competitors; 

    public StartRankingEvent(int judged_competitors) {
        super("START_RANKING");
        this.judged_competitors = judged_competitors;
    }

    public int getJudgedCompetitors() {
        return judged_competitors;
    }
    
}
