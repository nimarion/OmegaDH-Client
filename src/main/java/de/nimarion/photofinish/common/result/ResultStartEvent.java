package de.nimarion.photofinish.common.result;

import de.nimarion.photofinish.osv.Event;

public class ResultStartEvent extends Event {

    private String id;

    public ResultStartEvent(String id) {
        super("RESULT_START");
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
