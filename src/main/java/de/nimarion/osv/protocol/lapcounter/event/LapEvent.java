package de.nimarion.osv.protocol.lapcounter.event;

import de.nimarion.osv.protocol.lapcounter.LapCounterEvent;

public class LapEvent extends LapCounterEvent {

    private final int lap;

    public LapEvent(int lap) {
        super("LAP");
        this.lap = lap;
    }

    public int getLap() {
        return lap;
    }
}
