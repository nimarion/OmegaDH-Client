package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.gemini.GeminiEvent;

public class RunningTimeEvent extends GeminiEvent {

    private final String time;
    private final boolean finish;
    private int intermediate;

    public RunningTimeEvent(String time) {
        this(time,false);
    }

    public RunningTimeEvent(String time, boolean finish) {
        super("RUNNING_TIME");
        this.time = time;
        this.finish = finish;
    }

    public RunningTimeEvent(String time, int intermediate) {
        this(time, false);
        this.intermediate = intermediate;
    }

    public String getTime() {
        return time;
    }

    public boolean isFinish() {
        return finish;
    }

    public int getIntermediate() {
        return intermediate;
    }

}
