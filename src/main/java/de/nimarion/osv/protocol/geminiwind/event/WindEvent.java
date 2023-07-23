package de.nimarion.osv.protocol.geminiwind.event;

import de.nimarion.osv.protocol.geminiwind.GeminiWindEvent;

public class WindEvent extends GeminiWindEvent {

    private double wind;

    public WindEvent(double wind) {
        super("WIND");
        this.wind = wind;
    }

    public double getWind() {
        return wind;
    }

}
