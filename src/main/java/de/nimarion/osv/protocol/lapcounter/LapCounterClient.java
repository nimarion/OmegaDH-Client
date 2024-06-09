package de.nimarion.osv.protocol.lapcounter;

import de.nimarion.osv.SerialClient;
import de.nimarion.osv.protocol.lapcounter.packet.ClearLapCounterPacket;
import de.nimarion.osv.protocol.lapcounter.packet.LapPacket;

public class LapCounterClient extends SerialClient {

    public LapCounterClient(String comPort) {
        super(comPort);
        registerPacket(new LapPacket());
        registerPacket(new ClearLapCounterPacket());
    }

}
