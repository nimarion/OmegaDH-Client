package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

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
