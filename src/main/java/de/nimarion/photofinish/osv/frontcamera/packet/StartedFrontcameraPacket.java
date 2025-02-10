package de.nimarion.photofinish.osv.frontcamera.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.frontcamera.FrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.event.StartedFrontcameraEvent;

public class StartedFrontcameraPacket extends FrontcameraPacket {

    public StartedFrontcameraPacket() {
        super("FC1");
    }

    @Override
    public Event handleData(String data) {
        return new StartedFrontcameraEvent(data.trim());
    }

}
