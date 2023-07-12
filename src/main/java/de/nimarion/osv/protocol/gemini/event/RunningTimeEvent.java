package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.Event;

public class RunningTimeEvent extends Event{

    private final String time;
    private final int mode;

    public RunningTimeEvent(int mode, String time) {
        super("RUNNING_TIME");
        this.time = time;
        this.mode = mode;
    }

    public String getTime() {
        return time;
    }

    public int getMode() {
        return mode;
    }

}
