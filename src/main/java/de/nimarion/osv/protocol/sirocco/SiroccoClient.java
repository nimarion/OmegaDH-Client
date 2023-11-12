package de.nimarion.osv.protocol.sirocco;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fazecast.jSerialComm.SerialPort;

import de.nimarion.osv.Client;
import de.nimarion.osv.EventHandler;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

public class SiroccoClient extends Thread implements Client {

    private List<EventHandler> eventHandlers;
    private String comPort;

    public SiroccoClient(String comPort) {
        this.eventHandlers = new ArrayList<>();
        this.comPort = comPort;
    }

    @Override
    public void run() {
        SerialPort windPort = SerialPort.getCommPort(comPort);
        windPort.setComPortParameters(9600, 8, 1, 0);
        windPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
        if (!windPort.openPort()) {
            throw new RuntimeException("Failed to connect to " + comPort);
        }
        try (InputStream in = windPort.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            int read;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            while ((read = reader.read()) != -1) {
                if (read == 2) {
                    baos.reset();
                    continue;
                }
                if (read == 3) {
                    String[] parts = baos.toString().split(",");
                    if (parts.length >= 2) {
                        try {
                            float wind = (float) (Math.round(Float.parseFloat(parts[1]) * 10.0) / 10.0);
                            handleEvent(new WindEvent(wind));
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                    baos.reset();
                }
                baos.write(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleEvent(Event event) {
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.handleEvent(event);
        }
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        throw new UnsupportedOperationException("Unimplemented method 'handleData'");
    }

    @Override
    public void addEventHandler(EventHandler eventHandler) {
        eventHandlers.add(eventHandler);
    }

    @Override
    public void removeEventHandler(EventHandler eventHandler) {
        eventHandlers.remove(eventHandler);
    }

}
