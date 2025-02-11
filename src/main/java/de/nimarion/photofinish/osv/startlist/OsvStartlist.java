package de.nimarion.photofinish.osv.startlist;

import de.nimarion.photofinish.common.startlist.Startlist;

public class OsvStartlist extends Startlist {

    private final String date;
    private final String startTime;

    public OsvStartlist(String id, int distance, String title, String date, String startTime) {
        super(id, distance, title);
        this.startTime = startTime;
        this.date = date;
    }
    
    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

}
