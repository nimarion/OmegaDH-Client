package de.nimarion.photofinish.osv.frontcamera.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.frontcamera.FrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.event.DaytimeFrontcameraEvent;

public class DaytimeFrontcameraPacket extends FrontcameraPacket {

    public DaytimeFrontcameraPacket() {
        super("R00");
    }

    @Override
    public Event handleData(String data) {
        return new DaytimeFrontcameraEvent(data.trim());
    }
}
