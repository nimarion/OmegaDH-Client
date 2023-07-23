
package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class SupplementaryInfoDataEvent extends OmegaEvent {

    private final String dataString;

    public SupplementaryInfoDataEvent(String dataString) {
        super("SUPPLEMENTARY_INFO_DATA");
        this.dataString = dataString;
    }

    public String getDataString() {
        return dataString;
    }
    
}
