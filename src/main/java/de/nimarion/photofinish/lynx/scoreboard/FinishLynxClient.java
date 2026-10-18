package de.nimarion.photofinish.lynx.scoreboard;

import java.util.ArrayList;
import java.util.List;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.common.result.FullResultsEvent;
import de.nimarion.photofinish.common.result.ResultEndEvent;
import de.nimarion.photofinish.common.result.ResultEvent;
import de.nimarion.photofinish.common.result.ResultStartEvent;
import de.nimarion.photofinish.lynx.scoreboard.packet.ResultHeaderPacket;
import de.nimarion.photofinish.lynx.scoreboard.packet.ResultLinePacket;
import de.nimarion.photofinish.lynx.scoreboard.packet.ResultTrailerPacket;
import de.nimarion.photofinish.lynx.scoreboard.packet.RunningTimePacket;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class FinishLynxClient extends TCPClient {

    private final List<ResultEvent> results = new ArrayList<>();
    private String currentRaceId = null;

    public FinishLynxClient(String host, int port) {
        super(host, port);
        registerPacket(new ResultHeaderPacket());
        registerPacket(new ResultLinePacket());
        registerPacket(new ResultTrailerPacket());
        registerPacket(new RunningTimePacket());
    }

    public FinishLynxClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

    @Override
    public void handleEvent(Event event) {
        super.handleEvent(event);
        if(event instanceof ResultStartEvent){
            ResultStartEvent resultStartEvent = (ResultStartEvent) event;
            currentRaceId = getEventNumberFromId(resultStartEvent.getId()) + "-" + getRoundNumberFromId(resultStartEvent.getId()) + "-" +getHeatNumberFromId(resultStartEvent.getId());
            results.clear();
        }
        if(event instanceof ResultEvent){
            results.add((ResultEvent) event);
        }
        if(event instanceof ResultEndEvent){
            FullResultsEvent fullResultsEvent = new FullResultsEvent(currentRaceId, List.copyOf(results));
            super.handleEvent(fullResultsEvent);
            results.clear();
        }
    }

    public static int getEventNumberFromId(String id){
        return Integer.parseInt(id.split("-")[0]);
    }

    public static int getRoundNumberFromId(String id){
        return Integer.parseInt(id.split("-")[1]);
    }

    public static int getHeatNumberFromId(String id){
        return Integer.parseInt(id.split("-")[2]);
    }

}
