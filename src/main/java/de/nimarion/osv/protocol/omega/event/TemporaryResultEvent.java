package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class TemporaryResultEvent extends OmegaEvent {

    private final int bib;
    private final int lane;
    private final String time;

    public TemporaryResultEvent(int bib, int lane, String time) {
        super("TEMPORARY_RESULT");
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public int getBib() {
        return bib;
    }

    public int getLane() {
        return lane;
    }

    public String getTime() {
        return time;
    }
    
}
