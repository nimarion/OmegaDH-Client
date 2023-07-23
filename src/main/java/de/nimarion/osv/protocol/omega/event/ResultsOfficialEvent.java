package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

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
