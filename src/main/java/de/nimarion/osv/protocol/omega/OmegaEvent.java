package de.nimarion.osv.protocol.omega;

import de.nimarion.osv.protocol.Event;

public class OmegaEvent extends Event {

    public OmegaEvent(String type) {
        super("OMEGA_"  + type);
    }
    
}
