package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class CurrentDaytimeCursorEvent extends OmegaEvent {

    private final String time;

    public CurrentDaytimeCursorEvent(String time) {
        super("CURRENT_DAYTIME_CURSOR");
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    
}
