package de.nimarion.osv.protocol.omega.event;

import java.util.List;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class FullResultsEvent extends OmegaEvent{

    private final String raceId;
    private final List<ResultEvent> results;
    private final List<ReactionTimeEvent> reactionTimes;

    public FullResultsEvent(String raceId, List<ResultEvent> results, List<ReactionTimeEvent> reactionTimes) {
        super("FULL_RESULT");
        this.raceId = raceId;
        this.results = results;
        this.reactionTimes = reactionTimes;
    }

    public String getRaceId() {
        return raceId;
    }

    public List<ResultEvent> getResults() {
        return results;
    }

    public List<ReactionTimeEvent> getReactionTimes() {
        return reactionTimes;
    }
    
}
