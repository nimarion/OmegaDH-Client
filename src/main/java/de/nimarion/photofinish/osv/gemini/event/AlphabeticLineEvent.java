package de.nimarion.photofinish.osv.gemini.event;

import de.nimarion.photofinish.osv.gemini.GeminiEvent;

public class AlphabeticLineEvent extends GeminiEvent {

    private String text;
    private int line;

    public AlphabeticLineEvent(int line, String text) {
        super("ALPHABETIC_LINE");
        this.text = text;
        this.line = line;
    }

    public String getText() {
        return text;
    }

    public int getLine() {
        return line;
    }

}
