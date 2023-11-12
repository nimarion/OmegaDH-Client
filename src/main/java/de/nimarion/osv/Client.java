package de.nimarion.osv;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

public interface Client {

    public void handleEvent(Event event);

    public void handleData(Packet packet, byte[] data);

    public void addEventHandler(EventHandler eventHandler);

    public void removeEventHandler(EventHandler eventHandler);
    
}
