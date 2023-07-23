package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

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
