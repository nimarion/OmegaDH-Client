package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.Event;

public class IntermediateTimeEvent extends Event {

    private final int number;
    private final String time;

    public IntermediateTimeEvent(int number, String time) {
        super("INTERMEDIATE_TIME");
        this.number = number;
        this.time = time;
    }

    public int getNumber() {
        return number;
    }

    public String getTime() {
        return time;
    }

    
}
