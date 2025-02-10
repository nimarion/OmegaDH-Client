package de.nimarion.photofinish.osv.omega.event;

import de.nimarion.photofinish.osv.omega.OmegaEvent;

public class ResultEvent extends OmegaEvent {

    private final Integer rank;
    private final int bib;
    private final int lane;
    private final String time;
    private final String time_thousands;

    public ResultEvent(Integer rank, int bib, int lane, String time, String time_thousands) {
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

    public int getBib() {
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
