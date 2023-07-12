package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.Event;

public class LuminosityEvent extends Event{

    private int level;

    public LuminosityEvent(int level) {
        super("CHANGE_LUMINOSITY");
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    
}
