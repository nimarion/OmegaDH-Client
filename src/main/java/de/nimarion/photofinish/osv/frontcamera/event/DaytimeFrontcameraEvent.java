package de.nimarion.photofinish.osv.frontcamera.event;

import de.nimarion.photofinish.osv.frontcamera.FrontcameraEvent;

public class DaytimeFrontcameraEvent extends FrontcameraEvent{

    private final String daytime;

    public DaytimeFrontcameraEvent(String daytime) {
        super("DAYTIME_FRONTCAMERA");
        this.daytime = daytime;
    }

    public String getDaytime() {
        return daytime;
    }
    
}
