package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class ResultsOfficialEvent extends OmegaEvent {

    private final String raceId;

    public ResultsOfficialEvent(String raceId) {
        super("RESULTS_OFFICIAL");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
