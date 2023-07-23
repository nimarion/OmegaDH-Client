package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.gemini.GeminiEvent;

public class ResultEvent extends GeminiEvent {

    private final int line;
    private final Integer bib;
    private final Integer rank;
    private final Integer lane;
    private final String time;

    public ResultEvent(int line, int rank, int lane, String time) {
        super("RESULT");
        this.line = line;
        this.bib = null;
        this.rank = rank;
        this.lane = lane;
        this.time = time;
    }

    public ResultEvent(int line, Integer bib, String time) {
        super("RESULT");
        this.line = line;
        this.bib = bib;
        this.time = time;
        this.rank = null;
        this.lane = null;
    }

    public int getRank() {
        return rank;
    }

    public int getLane() {
        return lane;
    }

    public String getTime() {
        return time;
    }

    public Integer getBib() {
        return bib;
    }

    public int getLine() {
        return line;
    }

}
