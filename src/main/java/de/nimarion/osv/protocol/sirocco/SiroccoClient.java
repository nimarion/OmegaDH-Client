package de.nimarion.osv.protocol.sirocco;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import de.nimarion.osv.SerialClient;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;

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
