package de.nimarion.osv.protocol.omega.packet;

import java.util.Arrays;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.omega.OmegaPacket;
import de.nimarion.osv.protocol.omega.event.WindEvent;

/**
 * Name: [Wind, No_Wind]
 * Group: [RACING, RESULT]
 * Message: <SOH><DC3>DVRWM<STX>%0 %1<EOT>
 * Placeholder 0: WIND
 * Placeholder 1: UNIT
 */
public class WindPacket extends OmegaPacket {

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
                exception.printStackTrace(); 
            }
        }
        return new WindEvent(wind, wind_unit);
    }

}
