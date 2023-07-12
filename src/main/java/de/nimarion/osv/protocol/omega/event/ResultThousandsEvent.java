package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class ResultThousandsEvent extends Event {

    private final int rank;
    private final String bib;
    private final int lane;
    private final String time;

    public ResultThousandsEvent(int rank, String bib, int lane, String time) {
        super("RESULT_THOUSANDS");
        this.rank = rank;
        this.bib = bib;
        this.lane = lane;
        this.time = time;
    }

    public int getRank() {
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
