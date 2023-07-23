package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class EnterRaceEvent extends OmegaEvent {

    private final String raceId;

    public EnterRaceEvent(String raceId) {
        super("ENTER_RACE");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
