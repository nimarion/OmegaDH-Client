package de.nimarion.photofinish.osv.gemini;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.osv.ProtocolConfiguration;
import de.nimarion.photofinish.osv.gemini.packet.AlphaLinePacket;
import de.nimarion.photofinish.osv.gemini.packet.ClearDisplayPacket;
import de.nimarion.photofinish.osv.gemini.packet.DeleteLinePacket;
import de.nimarion.photofinish.osv.gemini.packet.LuminosityPacket;
import de.nimarion.photofinish.osv.gemini.packet.NetTimePacket;
import de.nimarion.photofinish.osv.gemini.packet.ResultLinePacket;
import de.nimarion.photofinish.osv.gemini.packet.RunningTimePacket;

public class GeminiClient extends TCPClient {

    public GeminiClient(String host, int port) {
        super(host, port);
        registerPacket(new AlphaLinePacket());
        registerPacket(new ClearDisplayPacket());
        registerPacket(new DeleteLinePacket());
        registerPacket(new LuminosityPacket());
        registerPacket(new NetTimePacket());
        registerPacket(new ResultLinePacket());
        registerPacket(new RunningTimePacket());
    }

    public GeminiClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

}
