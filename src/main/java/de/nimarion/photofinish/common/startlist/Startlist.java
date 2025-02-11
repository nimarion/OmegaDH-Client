package de.nimarion.photofinish.common.startlist;

import java.util.ArrayList;
import java.util.List;

public class Startlist {

    private final String id;
    private final int distance;
    private final String title;
    private final List<Competitor> members;

    public Startlist(String id,int distance, String title) {
        this.id = id;
        this.distance = distance;
        this.title = title;
        this.members = new ArrayList<>();
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

    public void addCompetitor(Competitor member) {
        members.add(member);
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
