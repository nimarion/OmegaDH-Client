package de.nimarion.photofinish;

import java.io.IOException;
import java.io.InputStream;

import com.fazecast.jSerialComm.SerialPort;

public class SerialClient extends Client {

    private SerialPort serialPort;

    public SerialClient(String comPort) {
        this.serialPort = SerialPort.getCommPort(comPort);
        this.serialPort.setComPortParameters(9600, 8, 1, 0);
        this.serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        if (!this.serialPort.openPort()) {
            throw new RuntimeException("Failed to connect to " + comPort);
        }
        System.out.println("Connected to " + comPort);
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
            e.printStackTrace();
        }
    }
 
}
