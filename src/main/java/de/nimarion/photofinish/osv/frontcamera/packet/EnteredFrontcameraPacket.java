package de.nimarion.photofinish.osv.frontcamera.packet;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.frontcamera.FrontcameraPacket;
import de.nimarion.photofinish.osv.frontcamera.event.EnteredFrontcameraEvent;

public class EnteredFrontcameraPacket extends FrontcameraPacket {

    public EnteredFrontcameraPacket() {
        super("FC0");
    }

    @Override
    public Event handleData(String data) {
        return new EnteredFrontcameraEvent(data.trim());
    }

}
