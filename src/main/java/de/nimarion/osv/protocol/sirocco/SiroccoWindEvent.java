package de.nimarion.osv.protocol.sirocco;

import de.nimarion.osv.protocol.Event;

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
