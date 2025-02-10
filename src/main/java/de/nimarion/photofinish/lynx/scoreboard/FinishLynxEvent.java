package de.nimarion.photofinish.lynx.scoreboard;

import de.nimarion.photofinish.osv.Event;

public class FinishLynxEvent extends Event {

    public FinishLynxEvent(String type) {
        super("LYNX_" + type);
    }
    
}
