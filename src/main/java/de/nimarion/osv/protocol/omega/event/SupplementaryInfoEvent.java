
package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

import java.util.Map;

public class SupplementaryInfoEvent extends OmegaEvent {

    private final Map<String, String> fields;

    public SupplementaryInfoEvent(Map<String, String> fields) {
        super("SUPPLEMENTARY_INFO");
        this.fields = fields;
    }

    public Map<String, String> getFields() {
        return fields;
    }
    
}
