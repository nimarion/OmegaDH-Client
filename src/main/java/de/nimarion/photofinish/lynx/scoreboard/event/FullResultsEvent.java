package de.nimarion.photofinish.lynx.scoreboard.event;

import java.util.List;

import de.nimarion.photofinish.common.result.ResultEvent;
import de.nimarion.photofinish.lynx.scoreboard.FinishLynxEvent;

public class FullResultsEvent extends FinishLynxEvent {

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
