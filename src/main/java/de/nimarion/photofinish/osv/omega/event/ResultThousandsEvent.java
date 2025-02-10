package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class ResultThousandsEvent extends OmegaEvent {

    private final int rank;
    private final int bib;
    private final int lane;
    private final String time;

    public ResultThousandsEvent(int rank, int bib, int lane, String time) {
        super("RESULT_THOUSANDS");
        this.rank = rank;
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public int getRank() {
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
