
package de.nimarion.photofinish.osv.omega.event;

import java.util.List;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class SupplementaryInfoHeaderEvent extends OmegaEvent {

    private final List<String> fields;
    private final List<Integer> lengths;
    

    public SupplementaryInfoHeaderEvent(List<String> fields, List<Integer> lengths) {
        super("SUPPLEMENTARY_INFO_HEADER");
        this.fields = fields;
        this.lengths = lengths;
    }

    public List<String> getFields() {
        return fields;
    }

    public List<Integer> getLengths() {
        return lengths;
    }
    
}
