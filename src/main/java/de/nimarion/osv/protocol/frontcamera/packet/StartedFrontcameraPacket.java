package de.nimarion.osv.protocol.frontcamera.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.frontcamera.FrontcameraPacket;
import de.nimarion.osv.protocol.frontcamera.event.StartedFrontcameraEvent;

public class StartedFrontcameraPacket extends FrontcameraPacket {

    public StartedFrontcameraPacket() {
        super("FC1");
    }

    @Override
    public Event handleData(String data) {
        return new StartedFrontcameraEvent(data.trim());
    }

}
