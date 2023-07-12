package de.nimarion.osv.protocol.frontcamera.event;

import de.nimarion.osv.protocol.Event;

public class ExitedFrontcameraEvent extends Event {

    private final String raceId;

    public ExitedFrontcameraEvent(String raceId) {
        super("EXITED_FRONTCAMERA");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
