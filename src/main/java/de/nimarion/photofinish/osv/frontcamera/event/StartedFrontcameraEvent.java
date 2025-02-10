package de.nimarion.photofinish.osv.frontcamera.event;

import de.nimarion.photofinish.osv.frontcamera.FrontcameraEvent;

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
