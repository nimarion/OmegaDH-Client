package de.nimarion.photofinish.osv.frontcamera.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.frontcamera.FrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.event.ExitedFrontcameraEvent;

public class ExitedFrontcameraPacket extends FrontcameraPacket {

    public ExitedFrontcameraPacket() {
        super("FC2");
    }

    @Override
    public Event handleData(String data) {
        return new ExitedFrontcameraEvent(data.trim());
    }

}
