package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class ResultHundredsEvent extends OmegaEvent {

    private final Integer rank;
    private final int bib;
    private final int lane;
    private final String time;

    public ResultHundredsEvent(Integer rank, int bib, int lane, String time) {
        super("RESULT_HUNDREDS");
        this.rank = rank;
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public Integer getRank() {
        return rank;
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
