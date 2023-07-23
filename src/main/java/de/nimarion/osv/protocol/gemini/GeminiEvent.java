package de.nimarion.osv.protocol.gemini;

import de.nimarion.osv.protocol.Event;

public class GeminiEvent extends Event {

    public GeminiEvent(String type) {
        super("GEMINI_" + type);
    }
    
}
