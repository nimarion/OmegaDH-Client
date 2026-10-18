package de.nimarion.photofinish.osv.omega.packet;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.omega.OmegaPacket;
import de.nimarion.photofinish.osv.omega.event.WindEvent;

/**
 * Name: [Wind, No_Wind]
 * Group: [RACING, RESULT]
 * Message: <SOH><DC3>DVRWM<STX>%0 %1<EOT>
 * Placeholder 0: WIND
 * Placeholder 1: UNIT
 */
public class WindPacket extends OmegaPacket {

    private static final Logger LOGGER = LoggerFactory.getLogger(WindPacket.class);

    public WindPacket() {
        super("RWM");
    }

    @Override
    public Event handleData(String information) {
        char[] data = information.toCharArray();
        String wind_unit = new String(Arrays.copyOfRange(data, 6, 9)).replaceAll("\\s+",
                "").trim();
        Double wind = null;
        String wind_string = new String(Arrays.copyOfRange(data, 0, 6)).replaceAll("\\s+",
                "").trim();
        if (!wind_string.isEmpty()) {
            try {
                wind = Double.parseDouble(
                        wind_string);
            } catch (NumberFormatException exception) {
                LOGGER.warn("Failed to parse Omega wind value from '{}'", wind_string, exception);
            }
        }
        return new WindEvent(wind, wind_unit);
    }

}
