package de.nimarion.osv;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

public abstract class TCPClient extends Thread {

    private static final Gson GSON = new GsonBuilder().create();
    private final String host;
    private final int port;
    private final List<Packet> packets;
    private int autoReconnectDelay = 5;
    private boolean active = true;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
        this.packets = new ArrayList<>();
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

    public void handleEvent(Event event) {
        System.out.println(GSON.toJson(event));
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
                                    System.out.println("Unknown packet: " + new String(bytes));
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
