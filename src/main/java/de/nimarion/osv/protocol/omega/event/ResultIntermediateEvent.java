package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class ResultIntermediateEvent extends OmegaEvent {

    private final int lap;
    private final int rank;
    private final int bib;
    private final int lane;
    private final String time;

    public ResultIntermediateEvent(int lap, int rank, int bib, int lane, String time) {
        super("RESULT_INTERMEDIATE");
        this.lap = lap;
        this.rank = rank;
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public int getLap() {
        return lap;
    }

    public int getRank() {
        return rank;
    }

    public int getLane() {
        return lane;
    }

    public int getBib() {
        return bib;
    }

    public String getTime() {
        return time;
    }
    
}
