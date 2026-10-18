package de.nimarion.photofinish.osv.sirocco;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;

public class WindPacket implements Packet {

    private static final Logger LOGGER = LoggerFactory.getLogger(WindPacket.class);

    @Override
    public boolean isPacket(byte[] data) {
        throw new UnsupportedOperationException("Unimplemented method 'isPacket'");
    }

    @Override
    public Event handleData(String data) {
        String[] parts = data.split(",");
        if (parts.length >= 2) {
            try {
                float wind = (float) (Math.round(Float.parseFloat(parts[1]) * 10.0) / 10.0);
                return new SiroccoWindEvent(wind);
            } catch (NumberFormatException e) {
                LOGGER.warn("Failed to parse Sirocco wind value from '{}'", data, e);
            }
        }
        return null;
    }

}
