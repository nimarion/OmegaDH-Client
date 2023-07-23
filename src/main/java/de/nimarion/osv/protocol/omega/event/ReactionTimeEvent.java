package de.nimarion.osv.protocol.omega.event;

import de.nimarion.osv.protocol.omega.OmegaEvent;

public class ReactionTimeEvent extends OmegaEvent {

    private final int bib;
    private final int lane;
    private final String reactionTime;
    private final String reactionFlag;

    public ReactionTimeEvent(int bib, int lane, String reactionTime, String reactionFlag) {
        super("REACTION_TIME");
        this.bib = bib;
        this.lane = lane;
        this.reactionTime = reactionTime;
        this.reactionFlag = reactionFlag;
    }

    public int getBib() {
        return bib;
    }

    public int getLane() {
        return lane;
    }

    public String getReactionTime() {
        return reactionTime;
    }

    public String getReactionFlag() {
        return reactionFlag;
    }
    
}
