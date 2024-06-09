
package de.nimarion.osv.protocol.omega.event;

import java.util.List;

import de.nimarion.osv.protocol.omega.OmegaEvent;
import de.nimarion.osv.protocol.omega.SupplementaryInfoResult;


public class SupplementaryInfoResultEvent extends OmegaEvent {

    private final List<SupplementaryInfoResult> results;

    public SupplementaryInfoResultEvent(List<SupplementaryInfoResult> results) {
        super("SUPPLEMENTARY_INFO_RESULT");
        this.results = results;
    }

    public List<SupplementaryInfoResult> getResults() {
        return results;
    }

}
