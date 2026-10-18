package de.nimarion.photofinish;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.nimarion.photofinish.osv.ProtocolConfiguration;

public abstract class TCPClient extends Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(TCPClient.class);

    private final String host;
    private final int port;
    private int autoReconnectDelay = 5;
    private volatile Socket currentSocket;

    public TCPClient(String host, int port) {
        LOGGER.info("Starting {} Client on {}:{}", this.getClass().getSimpleName(), host, port);
        this.host = host;
        this.port = port;
    }

    public TCPClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

    @Override
    public void run() {
        while (isActive() && !Thread.currentThread().isInterrupted()) {
            try (Socket socket = new Socket(host, port);
                    InputStream is = socket.getInputStream()) {
                this.currentSocket = socket;
                if (!isActive()) {
                    break;
                }
                handleInputstream(is);
            } catch (IOException e) {
                if (isActive()) {
                    LOGGER.error("Connection error on {}:{}", host, port, e);
                }
            } finally {
                this.currentSocket = null;
            }
            if (!isActive()) {
                break;
            }
            try {
                TimeUnit.SECONDS.sleep(autoReconnectDelay);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.warn("Reconnect delay interrupted", e);
                break;
            }
        }
    }

    @Override
    public void close() {
        super.close();
        Socket socket = this.currentSocket;
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                LOGGER.debug("Error closing socket on {}:{}", host, port, e);
            }
        }
    }

}
