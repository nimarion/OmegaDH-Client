package de.nimarion.osv.protocol.frontcamera.event;

import de.nimarion.osv.protocol.Event;

public class DaytimeFrontcameraEvent extends Event{

    private final String daytime;

    public DaytimeFrontcameraEvent(String daytime) {
        super("DAYTIME_FRONTCAMERA");
        this.daytime = daytime;
    }

    public String getDaytime() {
        return daytime;
    }
    
}
