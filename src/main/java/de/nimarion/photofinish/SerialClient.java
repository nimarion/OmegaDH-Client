package de.nimarion.photofinish;

import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SerialClient extends Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerialClient.class);

    private final SerialPort serialPort;

    public SerialClient(String comPort) {
        this.serialPort = SerialPort.getCommPort(comPort);
        this.serialPort.setComPortParameters(9600, 8, 1, 0);
        this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        if (!this.serialPort.openPort()) {
            throw new RuntimeException("Failed to connect to " + comPort);
        }
        LOGGER.info("Connected to {}", comPort);
    }

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public InputStream getInputStream() {
        return serialPort.getInputStream();
    }

    @Override
    public void run() {
        try (InputStream in = getInputStream()) {
            handleInputstream(in);
        } catch (IOException e) {
            if (isActive()) {
                LOGGER.error("Error reading from serial port", e);
            }
        } finally {
            closePort();
        }
    }

    @Override
    public void close() {
        super.close();
        closePort();
    }

    private void closePort() {
        if (serialPort != null && serialPort.isOpen()) {
            serialPort.closePort();
        }
    }

}
