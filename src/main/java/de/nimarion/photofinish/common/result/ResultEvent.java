package de.nimarion.photofinish.common.result;

import de.nimarion.photofinish.osv.Event;

public class ResultEvent extends Event {

    private final int place;
    private final int lane;
    private final int id;
    private final String time;
    private String reactionTime;
    private String timeThousands;

    public ResultEvent(int place, int lane, int id, String time, String timeThousands, String reactionTime) {
        super("RESULT");
        this.place = place;
        this.lane = lane;
        this.id = id;
        this.reactionTime = reactionTime;
        this.time = time;   
        this.timeThousands = timeThousands;
    }

    public Integer getPlace() {
        return place;
    }

    public Integer getLane() {
        return lane;
    }

    public Integer getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getReactionTime() {
        return reactionTime;
    }

    public String getTimeThousands() {
        return timeThousands;
    }

    public void setReactionTime(String reactionTime) {
        this.reactionTime = reactionTime;
    }

    public void setTimeThousands(String timeThousands) {
        this.timeThousands = timeThousands;
    }
    
}
