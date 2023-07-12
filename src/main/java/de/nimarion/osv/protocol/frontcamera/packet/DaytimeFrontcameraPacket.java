package de.nimarion.osv.protocol.frontcamera.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.frontcamera.FrontcameraPacket;
import de.nimarion.osv.protocol.frontcamera.event.DaytimeFrontcameraEvent;

public class DaytimeFrontcameraPacket extends FrontcameraPacket {

    public DaytimeFrontcameraPacket() {
        super("R00");
    }

    @Override
    public Event handleData(String data) {
        return new DaytimeFrontcameraEvent(data.trim());
    }
}
