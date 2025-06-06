package de.nimarion.photofinish;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.utils.StringUtils;

public abstract class Client extends Thread {

    private List<EventHandler> eventHandlers;
    private final List<Packet> packets;

    public Client() {
        this.eventHandlers = new ArrayList<>();
        this.packets = new ArrayList<>();
    }

    public void handleEvent(Event event) {
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }

    public void handleData(Packet packet, byte[] data) {
        Event event = packet.handleData(new String(data));
        if (event != null) {
            handleEvent(event);
        }
    }

    public void registerPacket(Packet packet) {
        packets.add(packet);
    }

    public void addEventHandler(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    public void removeEventHandler(EventHandler eventHandler) {
        eventHandlers.remove(eventHandler);
    }

    public Packet getPacket(byte[] data) {
        for (Packet packet : packets) {
            if (packet.isPacket(data)) {
                return packet;
            }
        }
        return null;
    }

    public void handleInputstream(InputStream inputStream) throws IOException {
        int read;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((read = inputStream.read()) != -1) {
            if (read == 0) {
                continue;
            }
            if (baos.size() == 0 && read != 1) {
                continue;
            }
            baos.write(read);
            if (read == 4) {
                try {
                    baos.write(0);
                    byte[] bytes = baos.toByteArray();
                    Packet packet = getPacket(bytes);
                    if (packet != null) {
                        handleData(packet, new String(bytes, "cp1252").getBytes());
                    } else {
                        System.out.println("Unknown packet: " + StringUtils.toAscii(new String(bytes))
                                + " -> " + new String(bytes));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    baos.reset();
                }
            }
        }
    }

}
