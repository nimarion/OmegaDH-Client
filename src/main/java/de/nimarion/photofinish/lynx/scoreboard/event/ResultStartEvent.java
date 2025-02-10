package de.nimarion.photofinish.lynx.scoreboard.event;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxEvent;

public class ResultStartEvent extends FinishLynxEvent {

    private String eventName;
    private String wind;
    private int eventNumber;
    private int roundNumber;
    private int heatNumber;
    private int numberOfParticipants;

    public ResultStartEvent(String eventName, String wind, int eventNumber, int roundNumber,
            int heatNumber, int numberOfParticipants) {
        super("RESULT_START");
        this.eventName = eventName;
        this.wind = wind;
        this.eventNumber = eventNumber;
        this.roundNumber = roundNumber;
        this.heatNumber = heatNumber;
        this.numberOfParticipants = numberOfParticipants;
    }

    public String getEventName() {
        return eventName;
    }

    public String getWind() {
        return wind;
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

    public int getNumberOfParticipants() {
        return numberOfParticipants;
    }

}
