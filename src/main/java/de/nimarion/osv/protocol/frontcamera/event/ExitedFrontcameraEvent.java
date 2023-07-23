package de.nimarion.osv.protocol.frontcamera.event;

import de.nimarion.osv.protocol.frontcamera.FrontcameraEvent;

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
