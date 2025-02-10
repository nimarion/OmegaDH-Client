package de.nimarion.photofinish.osv.geminiwind.event;

import de.nimarion.photofinish.osv.geminiwind.GeminiWindEvent;

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
