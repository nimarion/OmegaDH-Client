package de.nimarion.osv.protocol.lapcounter;

import de.nimarion.osv.protocol.Event;

public class LapCounterEvent extends Event {

    public LapCounterEvent(String type) {
        super("LAPCOUNTER_" + type);
    }
    
}
