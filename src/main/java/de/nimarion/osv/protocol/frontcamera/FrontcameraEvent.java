package de.nimarion.osv.protocol.frontcamera;

import de.nimarion.osv.protocol.Event;

public class FrontcameraEvent extends Event {

    public FrontcameraEvent(String type) {
        super("FRONTCAMERA_" + type);
    }

}
