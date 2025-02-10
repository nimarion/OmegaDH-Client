package de.nimarion.photofinish.osv.lapcounter;

import de.nimarion.photofinish.SerialClient;
import de.nimarion.photofinish.osv.lapcounter.packet.ClearLapCounterPacket;
import de.nimarion.photofinish.osv.lapcounter.packet.LapPacket;

public class LapCounterClient extends SerialClient {

    public LapCounterClient(String comPort) {
        super(comPort);
        registerPacket(new LapPacket());
        registerPacket(new ClearLapCounterPacket());
    }

}
