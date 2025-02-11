package de.nimarion.photofinish.osv.omega.event;

import java.util.List;

import de.nimarion.photofinish.common.result.ResultEvent;
import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class FullResultsEvent extends OmegaEvent{

    private final String raceId;
    private final List<ResultEvent> results;

    public FullResultsEvent(String raceId, List<ResultEvent> results) {
        super("FULL_RESULT");
        this.raceId = raceId;
        this.results = results;
    }

    public String getRaceId() {
        return raceId;
    }

    public List<ResultEvent> getResults() {
        return results;
    }
    
}
