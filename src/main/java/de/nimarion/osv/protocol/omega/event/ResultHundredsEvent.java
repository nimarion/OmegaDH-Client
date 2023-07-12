package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class ResultHundredsEvent extends Event {

    private final Integer rank;
    private final String bib;
    private final int lane;
    private final String time;

    public ResultHundredsEvent(Integer rank, String bib, int lane, String time) {
        super("RESULT_HUNDREDS");
        this.rank = rank;
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public Integer getRank() {
        return rank;
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
