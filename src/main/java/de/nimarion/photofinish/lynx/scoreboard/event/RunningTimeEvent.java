package de.nimarion.photofinish.lynx.scoreboard.event;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxEvent;

public class RunningTimeEvent extends FinishLynxEvent {

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
