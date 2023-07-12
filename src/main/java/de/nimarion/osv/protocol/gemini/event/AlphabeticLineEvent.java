package de.nimarion.osv.protocol.gemini.event;

import de.nimarion.osv.protocol.Event;

public class AlphabeticLineEvent extends Event {

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
