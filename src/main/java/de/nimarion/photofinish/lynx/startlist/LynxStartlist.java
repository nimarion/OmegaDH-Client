package de.nimarion.photofinish.lynx.startlist;

import de.nimarion.photofinish.common.startlist.Startlist;

public class LynxStartlist extends Startlist{

    private final int eventNumber;
    private final int roundNumber;
    private final int heatNumber;

    public LynxStartlist(int eventNumber, int roundNumber, int heatNumber, int distance, String title) {
        super(eventNumber + "-" + roundNumber + "-" + heatNumber, distance, title);
        this.eventNumber = eventNumber;
        this.roundNumber = roundNumber;
        this.heatNumber = heatNumber;
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

}
