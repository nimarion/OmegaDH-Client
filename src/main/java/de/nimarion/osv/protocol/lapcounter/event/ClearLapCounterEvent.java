package de.nimarion.osv.protocol.lapcounter.event;

import de.nimarion.osv.protocol.lapcounter.LapCounterEvent;

public class ClearLapCounterEvent extends LapCounterEvent {

    public ClearLapCounterEvent() {
        super("CLEAR");
    }
    
}
