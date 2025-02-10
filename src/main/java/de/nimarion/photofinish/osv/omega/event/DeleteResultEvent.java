package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class DeleteResultEvent extends OmegaEvent{

    private int bib;
    private int lane;

    public DeleteResultEvent(int bib, int lane) {
        super("DELETE_RESULT");
        this.bib = bib;
        this.lane = lane;
    }

    public int getBib() {
        return bib;
    }

    public int getLane() {
        return lane;
    }
    
}
