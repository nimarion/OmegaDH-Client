package de.nimarion.osv;

import de.nimarion.osv.protocol.Event;

public abstract class EventHandler {

    public abstract void handleEvent(Event event);    
}
