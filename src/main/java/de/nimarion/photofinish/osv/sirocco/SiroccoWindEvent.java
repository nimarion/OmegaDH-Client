package de.nimarion.photofinish.osv.sirocco;

import de.nimarion.photofinish.osv.Event;

public class SiroccoWindEvent extends Event{

    private float wind;

    public SiroccoWindEvent(float wind) {
        super("SIROCCO_WIND");
        this.wind = wind;
    }

    public float getWind() {
        return wind;
    }
    
}
