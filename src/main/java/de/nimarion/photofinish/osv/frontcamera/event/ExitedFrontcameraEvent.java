package de.nimarion.photofinish.osv.frontcamera.event;

import de.nimarion.photofinish.osv.frontcamera.FrontcameraEvent;

public class ExitedFrontcameraEvent extends FrontcameraEvent {

    private final String raceId;

    public ExitedFrontcameraEvent(String raceId) {
        super("EXITED_FRONTCAMERA");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
