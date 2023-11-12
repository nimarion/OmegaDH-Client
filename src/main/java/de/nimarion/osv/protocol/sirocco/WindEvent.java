package de.nimarion.osv.protocol.sirocco;

import de.nimarion.osv.protocol.Event;

public class WindEvent extends Event {

    private float wind; 

    public WindEvent(float wind) {
        super("SIROCCO_WIND");
        this.wind = wind;
    }

    public float getWind() {
        return wind;
    }

}
    
