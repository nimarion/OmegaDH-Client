package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class ResultEvent extends Event {

    private final Integer rank;
    private final String bib;
    private final int lane;
    private final String time;
    private final String time_thousands;

    public ResultEvent(Integer rank, String bib, int lane, String time, String time_thousands) {
        super("RESULT");
        this.rank = rank;
        this.bib = bib;
        this.lane = lane;
        this.time = time;
        this.time_thousands = time_thousands;
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

    public String getTimeThousands() {
        return time_thousands;
    }
    
}
