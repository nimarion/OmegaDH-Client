package de.nimarion.photofinish.common.result;

import java.util.ArrayList;
import java.util.List;

import de.nimarion.photofinish.osv.Event;

public class FullResultsEvent extends Event{

    private final String raceId;
    private List<ResultEvent> results;

    public FullResultsEvent(String raceId, List<ResultEvent> results) {
        super("FULL_RESULTS");
        this.raceId = raceId;
        List<ResultEvent> sortedResults = new ArrayList<ResultEvent>(results);
        sortedResults.sort((o1, o2) -> o1.getPlace() - o2.getPlace());
        this.results = sortedResults;
    }

    public String getRaceId() {
        return raceId;
    }

    public List<ResultEvent> getResults() {
        return results;
    }
    
}
