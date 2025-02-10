package de.nimarion.photofinish.osv.lapcounter.event;

import de.nimarion.photofinish.osv.lapcounter.LapCounterEvent;

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
