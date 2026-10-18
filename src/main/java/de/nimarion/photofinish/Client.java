package de.nimarion.photofinish;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.utils.StringUtils;

public abstract class Client extends Thread implements AutoCloseable {

    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    protected static final Charset CP1252 = Charset.forName("Cp1252");

    private final List<EventHandler> eventHandlers;
    private final List<Packet> packets;
    private volatile boolean active = true;

    public Client() {
        this.eventHandlers = new CopyOnWriteArrayList<>();
        this.packets = new ArrayList<>();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public void close() {
        this.active = false;
        this.interrupt();
    }

    public void handleEvent(Event event) {
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }

    public void handleData(Packet packet, byte[] data) {
        Event event = packet.handleData(new String(data, CP1252));
        if (event != null) {
            handleEvent(event);
        }
    }

    protected void handleFramedData(Packet packet, byte[] data) {
        Event event;
        if (data.length <= 9) {
            event = packet.handleData(null);
        } else {
            event = packet.handleData(new String(data, 8, data.length - 10, CP1252));
        }
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
        while (active && (read = inputStream.read()) != -1) {
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
                        handleData(packet, bytes);
                    } else {
                        String decoded = new String(bytes, CP1252);
                        LOGGER.warn("Unknown packet: {} -> {}", StringUtils.toAscii(decoded), decoded);
                    }
                } catch (Exception ex) {
                    LOGGER.error("Failed to handle incoming packet", ex);
                } finally {
                    baos.reset();
                }
            }
        }
    }

}
