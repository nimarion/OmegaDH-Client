package de.nimarion.photofinish;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.Socket;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.reflections.Reflections;

import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

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
                if (!hasParameterlessPublicConstructor(packetClass)) {
                    continue;
                }
                final Packet packet = packetClass.getDeclaredConstructor().newInstance();
                registerPacket(packet);
            } catch (Exception exception) {
                System.out.println("Failed to register " + packetClass.getSimpleName() + " Packet");
                exception.printStackTrace();
            }
        }
    }

    private boolean hasParameterlessPublicConstructor(Class<?> clazz) {
        for (Constructor<?> constructor : clazz.getConstructors()) {
            if (constructor.getParameterCount() == 0) {
                return true;
            }
        }
        return false;
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
