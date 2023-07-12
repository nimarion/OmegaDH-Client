package de.nimarion.osv.protocol.frontcamera.packet;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.frontcamera.FrontcameraPacket;
import de.nimarion.osv.protocol.frontcamera.event.ExitedFrontcameraEvent;

public class ExitedFrontcameraPacket extends FrontcameraPacket {

    public ExitedFrontcameraPacket() {
        super("FC2");
    }

    @Override
    public Event handleData(String data) {
        return new ExitedFrontcameraEvent(data.trim());
    }

}
