package de.nimarion.osv.protocol.frontcamera.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.frontcamera.FrontcameraPacket;
import de.nimarion.osv.protocol.frontcamera.event.EnteredFrontcameraEvent;

public class EnteredFrontcameraPacket extends FrontcameraPacket {

    public EnteredFrontcameraPacket() {
        super("FC0");
    }

    @Override
    public Event handleData(String data) {
        return new EnteredFrontcameraEvent(data.trim());
    }

}
