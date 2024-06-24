package de.nimarion.osv.protocol.omega.event;

import java.util.List;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class FullResultsEvent extends OmegaEvent{

    private final List<ResultEvent> results;
    private final List<ReactionTimeEvent> reactionTimes;

    public FullResultsEvent(List<ResultEvent> results, List<ReactionTimeEvent> reactionTimes) {
        super("FULL_RESULT");
        this.results = results;
        this.reactionTimes = reactionTimes;
    }

    public List<ResultEvent> getResults() {
        return results;
    }

    public List<ReactionTimeEvent> getReactionTimes() {
        return reactionTimes;
    }
    
}
