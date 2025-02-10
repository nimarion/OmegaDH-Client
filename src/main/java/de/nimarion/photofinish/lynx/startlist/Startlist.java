package de.nimarion.photofinish.lynx.startlist;

import java.util.ArrayList;
import java.util.List;

public class Startlist {

    private final int eventNumber;
    private final int roundNumber;
    private final int heatNumber;
    private final int distance;
    private final String title;
    private final String date;
    private final String startTime;
    private final List<Competitor> members;

    public Startlist(int eventNumber, int roundNumber, int heatNumber, int distance, String title, String date, String startTime) {
        this.eventNumber = eventNumber;
        this.roundNumber = roundNumber;
        this.heatNumber = heatNumber;
        this.distance = distance;
        this.title = title;
        this.members = new ArrayList<>();
        this.startTime = startTime;
        this.date = date;
    }

    public void addCompetitor(Competitor member) {
        members.add(member);
    }

    public int getEventNumber() {
        return eventNumber;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getHeatNumber() {
        return heatNumber;
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

    public Competitor getCompetitorByBib(int bib) {
        for (Competitor member : members) {
            if (member.getBib() == bib) {
                return member;
            }
        }
        return null;
    }

    public Competitor getCompetitorByLane(int lane) {
        for (Competitor member : members) {
            if (member.getLane() == lane) {
                return member;
            }
        }
        return null;
    }

}
