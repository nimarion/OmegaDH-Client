
package de.nimarion.osv.protocol.omega.event;

import java.util.HashMap;
import java.util.Map;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class SupplementaryInfoDataEvent extends OmegaEvent {

    private final String dataString;
    private final Map<String, String> dataMap = new HashMap<>();

    public SupplementaryInfoDataEvent(String dataString) {
        super("SUPPLEMENTARY_INFO_DATA");
        this.dataString = dataString;
    }

    public String getDataString() {
        return dataString;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }

    public void addData(String key, String value) {
        dataMap.put(key, value);
    }
    
}
