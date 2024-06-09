package de.nimarion.osv;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.reflections.Reflections;

import de.nimarion.osv.protocol.Packet;
import de.nimarion.osv.protocol.ProtocolConfiguration;

public abstract class TCPClient extends Client {

    private final String host;
    private final int port;
    private int autoReconnectDelay = 5;
    private boolean active = true;

    public TCPClient(String host, int port) {
        System.out.println("Starting " + this.getClass().getSimpleName() + " Client on " + host + ":" + port);
        this.host = host;
        this.port = port;
        final Set<Class<? extends Packet>> packets = new Reflections(this.getClass().getPackageName() + ".packet")
                .getSubTypesOf(Packet.class);
        for (Class<? extends Packet> packetClass : packets) {
            try {
                final Packet packet = packetClass.getDeclaredConstructor().newInstance();
                registerPacket(packet);
                System.out.println("Registered " + packet.getClass().getSimpleName() + " Packet");
            } catch (Exception exception) {
                System.out.println("Failed to register " + packetClass.getSimpleName() + " Packet");
                exception.printStackTrace();
            }
        }
    }

    public TCPClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

    @Override
    public void run() {
        while (true) {
            if (active) {
                try (Socket socket = new Socket(host, port);
                        InputStream is = socket.getInputStream()) {
                    handleInputstream(is);
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
