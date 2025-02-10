package de.nimarion.photofinish.osv.gemini.event;

import de.nimarion.photofinish.osv.gemini.GeminiEvent;

public class LuminosityEvent extends GeminiEvent{

    private int level;

    public LuminosityEvent(int level) {
        super("CHANGE_LUMINOSITY");
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    
}
