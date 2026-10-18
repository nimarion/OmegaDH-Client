package de.nimarion.photofinish;

import de.nimarion.photofinish.osv.Event;

@FunctionalInterface
public interface EventHandler {

    void handleEvent(Event event);
}
