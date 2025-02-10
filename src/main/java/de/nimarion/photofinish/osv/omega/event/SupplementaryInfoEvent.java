
package de.nimarion.photofinish.osv.omega.event;

import java.util.Map;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

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
