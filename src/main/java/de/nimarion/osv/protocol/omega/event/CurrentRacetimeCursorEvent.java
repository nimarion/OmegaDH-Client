
package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class CurrentRacetimeCursorEvent extends Event {

    private final String time;

    public CurrentRacetimeCursorEvent(String time) {
        super("CURRENT_RACETIME_CURSOR");
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    
}
