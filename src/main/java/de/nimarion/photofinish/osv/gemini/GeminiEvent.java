package de.nimarion.photofinish.osv.gemini;

import de.nimarion.photofinish.osv.Event;

public class GeminiEvent extends Event {

    public GeminiEvent(String type) {
        super("GEMINI_" + type);
    }
    
}
