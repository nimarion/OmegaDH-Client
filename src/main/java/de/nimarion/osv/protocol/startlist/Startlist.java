package de.nimarion.osv.protocol.startlist;

import java.util.ArrayList;
import java.util.List;

public class Startlist {

    private final String id;
    private final int distance;
    private final String title;
    private final String date;
    private final String startTime;
    private final List<Competitor> members;

    public Startlist(String id, int distance, String title, String date, String startTime) {
        this.id = id;
        this.distance = distance;
        this.title = title;
        this.members = new ArrayList<>();
        this.startTime = startTime;
        this.date = date;
    }

    public void addCompetitor(Competitor member) {
        members.add(member);
    }

    public String getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public String getTitle() {
        return title;
    }

    public List<Competitor> getCompetitors() {
        return members;
    }

    public String getDate() {
        return date;
    }

    public String getStartTime() {
        return startTime;
    }

}
