package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.gemini.GeminiEvent;

public class ClearDisplayEvent extends GeminiEvent {

    public ClearDisplayEvent() {
        super("CLEAR_DISPLAY");
    }

}
