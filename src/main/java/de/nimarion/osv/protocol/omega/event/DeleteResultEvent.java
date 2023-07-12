package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class DeleteResultEvent extends Event{

    private String bib;
    private int lane;

    public DeleteResultEvent(String bib, int lane) {
        super("DELETE_RESULT");
        this.bib = bib;
        this.lane = lane;
    }

    public String getBib() {
        return bib;
    }

    public int getLane() {
        return lane;
    }
    
}
