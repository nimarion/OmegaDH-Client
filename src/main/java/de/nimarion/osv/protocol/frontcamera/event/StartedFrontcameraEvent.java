package de.nimarion.osv.protocol.frontcamera.event;

import de.nimarion.osv.protocol.frontcamera.FrontcameraEvent;

public class StartedFrontcameraEvent extends FrontcameraEvent {

    private final String raceId;

    public StartedFrontcameraEvent(String raceId) {
        super("STARTED_FRONTCAMERA");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
