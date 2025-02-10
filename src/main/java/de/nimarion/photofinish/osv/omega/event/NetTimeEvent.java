package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class NetTimeEvent extends OmegaEvent {

    private String time;

    public NetTimeEvent(String time) {
        super("NET_TIME");
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    
}
