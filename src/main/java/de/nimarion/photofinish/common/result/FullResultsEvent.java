package de.nimarion.photofinish.common.result;

import java.util.List;

import de.nimarion.photofinish.osv.Event;

public class FullResultsEvent extends Event{

    private final String raceId;
    private final List<ResultEvent> results;

    public FullResultsEvent(String raceId, List<ResultEvent> results) {
        super("FULL_RESULTS");
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
