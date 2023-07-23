package de.nimarion.osv.protocol.geminiwind;

import de.nimarion.osv.protocol.Event;

public class GeminiWindEvent extends Event {

    public GeminiWindEvent(String type) {
        super("GEMINI_WIND_" +  type);
    }
    
}
