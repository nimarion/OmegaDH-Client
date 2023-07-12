package de.nimarion.osv.protocol.frontcamera.event;

import de.nimarion.osv.protocol.Event;

public class StartedFrontcameraEvent extends Event {

    private final String raceId;

    public StartedFrontcameraEvent(String raceId) {
        super("STARTED_FRONTCAMERA");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
