package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class CurrentDaytimeCursorEvent extends Event {

    private final String time;

    public CurrentDaytimeCursorEvent(String time) {
        super("CURRENT_DAYTIME_CURSOR");
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    
}
