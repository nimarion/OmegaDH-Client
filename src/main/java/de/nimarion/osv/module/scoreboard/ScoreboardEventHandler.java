package de.nimarion.osv.module.scoreboard;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import de.nimarion.osv.EventHandler;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.gemini.event.ClearDisplayEvent;
import de.nimarion.osv.protocol.gemini.event.RunningTimeEvent;
import de.nimarion.osv.utils.NetworkUtils;

public class ScoreboardEventHandler extends EventHandler {

    private final List<String> receivers;
    private final InetSocketAddress bindNetworkInterfaceAddress;

    public ScoreboardEventHandler(String receiver, String bindNetworkInterfaceAddress) throws IOException {
        this(List.of(receiver), bindNetworkInterfaceAddress);
    }

    public ScoreboardEventHandler(List<String> receivers,  String bindNetworkInterfaceAddress) throws IOException {
        this.receivers = receivers;
        this.bindNetworkInterfaceAddress = new InetSocketAddress(bindNetworkInterfaceAddress, NetworkUtils.getRandomFreePort());
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof RunningTimeEvent) {
            RunningTimeEvent runningTimeEvent = (RunningTimeEvent) event;
            int colons = runningTimeEvent.getTime().length() - runningTimeEvent.getTime().replace(":", "").length();
            String time = runningTimeEvent.getTime();
            if (colons <= 2) {
                if (colons == 2) {
                    int secondColonIndex = time.lastIndexOf(':');
                    time = time.substring(0, secondColonIndex);
                } else if (colons == 1) {
                    int charTillCollon = time.chars().takeWhile(c -> c != ':').toArray().length;
                    if (charTillCollon == 2) {
                        int dotIndex = time.indexOf('.');
                        time = time.substring(0, dotIndex);
                    }
                }
                NetworkUtils.broadcastUDP(time.getBytes(), receivers, 5000, bindNetworkInterfaceAddress);
            }
        }
        if (event instanceof ClearDisplayEvent) {
            NetworkUtils.broadcastUDP("".getBytes(), receivers, 5000, bindNetworkInterfaceAddress);
        }
    }

}
