package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class ResultIntermediateEvent extends Event {

    private final int lap;
    private final int rank;
    private final String bib;
    private final int lane;
    private final String time;

    public ResultIntermediateEvent(int lap, int rank, String bib, int lane, String time) {
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

    public String getBib() {
        return bib;
    }

    public String getTime() {
        return time;
    }
    
}
