package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.Event;

public class WindEvent extends Event{

    private Double wind;
    private String unit;

    public WindEvent(Double wind, String unit) {
        super("WIND");
        this.wind = wind;
        this.unit = unit;
    }

    public Double getWind() {
        return wind;
    }

    public String getUnit() {
        return unit;
    }
    
}
