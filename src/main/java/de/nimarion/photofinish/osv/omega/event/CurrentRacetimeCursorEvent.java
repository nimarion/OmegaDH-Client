
package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

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
