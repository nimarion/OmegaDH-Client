package de.nimarion.osv.protocol.startlist;

import java.util.ArrayList;
import java.util.List;

public class StartlistEvent {

    private final String id;
    private final int distance;
    private final String title;
    private final List<StartlistMember> members;

    public StartlistEvent(String id, int distance, String title) {
        this.id = id;
        this.distance = distance;
        this.title = title;
        this.members = new ArrayList<>();
    }

    public void addMember(StartlistMember member) {
        members.add(member);
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the members
     */
    public List<StartlistMember> getMembers() {
        return members;
    }

}
