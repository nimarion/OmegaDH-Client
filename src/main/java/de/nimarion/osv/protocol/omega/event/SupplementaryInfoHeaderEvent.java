
package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

import java.util.List;

public class SupplementaryInfoHeaderEvent extends Event {

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
