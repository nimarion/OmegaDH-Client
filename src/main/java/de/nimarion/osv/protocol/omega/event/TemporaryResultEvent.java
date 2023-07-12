package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class TemporaryResultEvent extends Event {

    private final String bib;
    private final int lane;
    private final String time;

    public TemporaryResultEvent(String bib, int lane, String time) {
        super("TEMPORARY_RESULT");
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public String getBib() {
        return bib;
    }

    public int getLane() {
        return lane;
    }

    public String getTime() {
        return time;
    }
    
}
