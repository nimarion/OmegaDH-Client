package de.nimarion.photofinish.lynx.result;

public class LynxEvent {

    private final String id;
    private int eventNumber;
    private int roundNumber;
    private int heatNumber;
    private final String eventName;
    private final Float wind;
    private final String windUnit;
    private final String template;
    private final String captureTime;
    private final String caputureDuration;
    private final String distance;
    private final String startTime;
    private final String windMode;

    public LynxEvent(int eventNumber, int roundNumber, int heatNumber, String eventName, Float wind,
            String windUnit, String template, String captureTime, String caputureDuration, String distance,
            String startTime, String windMode) {
        this.id = eventNumber + "-" + roundNumber + "-" + heatNumber;
        this.eventNumber = eventNumber;
        this.roundNumber = roundNumber;
        this.heatNumber = heatNumber;
        this.eventName = eventName;
        this.wind = wind;
        this.windUnit = windUnit;
        this.template = template;
        this.captureTime = captureTime;
        this.caputureDuration = caputureDuration;
        this.distance = distance;
        this.startTime = startTime;
        this.windMode = windMode;
    }

    public String getId() {
        return id;
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

    public String getEventName() {
        return eventName;
    }

    public Float getWind() {
        return wind;
    }

    public String getWindUnit() {
        return windUnit;
    }

    public String getTemplate() {
        return template;
    }

    public String getCaptureTime() {
        return captureTime;
    }

    public String getCaputureDuration() {
        return caputureDuration;
    }

    public String getDistance() {
        return distance;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getWindMode() {
        return windMode;
    }
    
}
