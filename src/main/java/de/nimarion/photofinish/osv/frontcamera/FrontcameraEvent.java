package de.nimarion.photofinish.osv.frontcamera;

import de.nimarion.photofinish.osv.Event;

public class FrontcameraEvent extends Event {

    public FrontcameraEvent(String type) {
        super("FRONTCAMERA_" + type);
    }

}
