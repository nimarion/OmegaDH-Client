
package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class CurrentRacetimeCursorEvent extends OmegaEvent {

    private final String time;

    public CurrentRacetimeCursorEvent(String time) {
        super("CURRENT_RACETIME_CURSOR");
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    
}
