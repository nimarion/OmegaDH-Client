package de.nimarion.photofinish.lynx.startlist;

public class StartlistEvent {

    private int eventNumber;
    private int roundNumber;
    private int heatNumber;

    public StartlistEvent(int eventNumber, int roundNumber, int heatNumber) {
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
