package de.nimarion.osv.protocol.frontcamera.event;

import de.nimarion.osv.protocol.Event;

public class EnteredFrontcameraEvent extends Event {

    private final String raceId;

    public EnteredFrontcameraEvent(String raceId) {
        super("ENTERED_FRONTCAMERA");
        this.raceId = raceId;
    }

    public String getRaceId() {
        return raceId;
    }
    
}
