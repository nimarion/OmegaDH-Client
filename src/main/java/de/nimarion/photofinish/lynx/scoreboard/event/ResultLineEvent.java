package de.nimarion.photofinish.lynx.scoreboard.event;

import de.nimarion.photofinish.lynx.scoreboard.FinishLynxEvent;

public class ResultLineEvent extends FinishLynxEvent {

    private Integer place;
    private Integer lane;
    private Integer id;
    private String name;
    private String affilation;
    private String time;
    private String reactionTime;

    public ResultLineEvent(Integer place, Integer lane, Integer id, String name, String affilation, String time, String reactionTime) {
        super("RESULT_LINE");
        this.place = place;
        this.lane = lane;
        this.id = id;
        this.name = name;
        this.affilation = affilation;
        this.time = time;
        this.reactionTime = reactionTime;
    }

    public int getPlace() {
        return place;
    }

    public int getLane() {
        return lane;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAffilation() {
        return affilation;
    }

    public String getTime() {
        return time;
    }

    public String getReactionTime() {
        return reactionTime;
    }
  
}
