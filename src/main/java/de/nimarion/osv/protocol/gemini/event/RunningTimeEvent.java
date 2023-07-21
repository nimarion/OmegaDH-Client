package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.Event;

public class RunningTimeEvent extends Event {

    private final String time;

    public RunningTimeEvent(String time) {
        super("RUNNING_TIME");
        this.time = time;
    }

    public String getTime() {
        return time;
    }

}
