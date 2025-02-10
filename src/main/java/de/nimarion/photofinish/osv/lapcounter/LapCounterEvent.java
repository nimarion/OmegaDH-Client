package de.nimarion.photofinish.osv.lapcounter;

import de.nimarion.photofinish.osv.Event;

public class LapCounterEvent extends Event {

    public LapCounterEvent(String type) {
        super("LAPCOUNTER_" + type);
    }
    
}
