package de.nimarion.photofinish.osv.omega;

import de.nimarion.photofinish.osv.Event;

public class OmegaEvent extends Event {

    public OmegaEvent(String type) {
        super("OMEGA_"  + type);
    }
    
}
