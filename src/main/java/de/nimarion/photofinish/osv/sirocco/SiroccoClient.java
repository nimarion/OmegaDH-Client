package de.nimarion.photofinish.osv.sirocco;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.nimarion.photofinish.SerialClient;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;

public class SiroccoClient extends SerialClient {

    public SiroccoClient(String comPort) {
        super(comPort);
    }

    @Override
    public void handleInputstream(InputStream inputStream) throws IOException {
        int read;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        while ((read = inputStream.read()) != -1) {
            baos.write(read);
            if (read == 3) {
                Packet packet = new WindPacket();
                Event event = packet.handleData(new String(baos.toString()));
                if(event != null){
                    handleEvent(event);
                }
                baos.reset();
            }
             if(read == 10){
                baos.reset();   
            }
        }
    }

}
