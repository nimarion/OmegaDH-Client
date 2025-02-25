package de.nimarion.photofinish.lynx.result;

import java.util.List;

public class LynxResults {

    private final LynxEvent event;
    private final List<LynxResult> results;

    public LynxResults(LynxEvent event, List<LynxResult> results) {
        this.event = event;
        this.results = results;
    }

    public LynxEvent getEvent() {
        return event;
    }

    public List<LynxResult> getResults() {
        return results;
    }
    
}
