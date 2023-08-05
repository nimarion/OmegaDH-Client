package de.nimarion.osv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.reflections.Reflections;

import com.google.gson.Gson;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;
import de.nimarion.osv.protocol.ProtocolConfiguration;
import de.nimarion.osv.utils.StringUtils;

public abstract class TCPClient extends Thread {

    private final String host;
    private final int port;
    private final List<Packet> packets;
    private int autoReconnectDelay = 5;
    private boolean active = true;
    private List<EventHandler> eventHandlers;

    public TCPClient(String host, int port) {
        System.out.println("Starting " + this.getClass().getSimpleName() + " Client on " + host + ":" + port);
        this.host = host;
        this.port = port;
        this.packets = new ArrayList<>();
        this.eventHandlers = new ArrayList<>();
        final Set<Class<? extends Packet>> packets = new Reflections(this.getClass().getPackageName() + ".packet")
                .getSubTypesOf(Packet.class);
        for (Class<? extends Packet> packetClass : packets) {
            try {
                final Packet packet = packetClass.getDeclaredConstructor().newInstance();
                if (this.packets.add(packet)) {
                    System.out.println("Registered " + packet.getClass().getSimpleName() + " Packet");
                }
            } catch (Exception exception) {
                System.out.println("Failed to register " + packetClass.getSimpleName() + " Packet");
                exception.printStackTrace();
            }
        }
    }

    public TCPClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

    private Packet getPacket(byte[] data) {
        for (Packet packet : packets) {
            if (packet.isPacket(data)) {
                return packet;
            }
        }
        return null;
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

    public void handleEvent(Event event) {
        System.out.println(new Gson().toJson(event));
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }

    public abstract void handleData(Packet packet, byte[] data);

    @Override
    public void run() {
        while (true) {
            if (active) {
                try (Socket socket = new Socket(host, port);
                        InputStream is = socket.getInputStream()) {
                    int read;
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    while ((read = is.read()) != -1) {
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
                                    System.out.println("Unknown packet: " + StringUtils.toAscii(new String(bytes)));
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            } finally {
                                baos.reset();
                            }

                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                TimeUnit.SECONDS.sleep(autoReconnectDelay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
