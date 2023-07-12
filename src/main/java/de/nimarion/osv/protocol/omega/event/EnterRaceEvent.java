package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class EnterRaceEvent extends Event {

    private final String raceId;

    public EnterRaceEvent(String raceId) {
        super("ENTER_RACE");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
