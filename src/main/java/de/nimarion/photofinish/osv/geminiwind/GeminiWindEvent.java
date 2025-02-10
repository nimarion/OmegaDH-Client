package de.nimarion.photofinish.osv.geminiwind;

import de.nimarion.photofinish.osv.Event;

public class GeminiWindEvent extends Event {

    public GeminiWindEvent(String type) {
        super("GEMINI_WIND_" +  type);
    }
    
}
