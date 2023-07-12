package de.nimarion.osv.protocol.gemini;

import de.nimarion.osv.TCPClient;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;
import de.nimarion.osv.protocol.gemini.packet.AlphaLinePacket;
import de.nimarion.osv.protocol.gemini.packet.ClearDisplayPacket;
import de.nimarion.osv.protocol.gemini.packet.LuminosityPacket;
import de.nimarion.osv.protocol.gemini.packet.ResultLinePacket;
import de.nimarion.osv.protocol.gemini.packet.RunningTimePacket;

public class GeminiClient extends TCPClient {

    public GeminiClient(String host, int port) {
        super(host, port);
        registerPacket(new ClearDisplayPacket());
        registerPacket(new LuminosityPacket());
        registerPacket(new RunningTimePacket());
        registerPacket(new AlphaLinePacket());
        registerPacket(new ResultLinePacket());
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        Event event = packet.handleData(new String(data));
        if (event != null) {
            handleEvent(event);
        }
    }

}
