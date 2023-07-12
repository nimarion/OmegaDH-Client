
package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class SupplementaryInfoDataEvent extends Event {

    private final String dataString;

    public SupplementaryInfoDataEvent(String dataString) {
        super("SUPPLEMENTARY_INFO_DATA");
        this.dataString = dataString;
    }

    public String getDataString() {
        return dataString;
    }
    
}
