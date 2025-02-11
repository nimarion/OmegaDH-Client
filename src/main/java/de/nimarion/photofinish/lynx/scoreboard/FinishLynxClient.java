package de.nimarion.photofinish.lynx.scoreboard;

import java.util.ArrayList;
import java.util.List;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.common.result.FullResultsEvent;
import de.nimarion.photofinish.common.result.ResultEvent;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;

public class FinishLynxClient extends TCPClient {

    private final List<ResultEvent> results = new ArrayList<>();
    private String currentRaceId = null;

    public FinishLynxClient(String host, int port) {
        super(host, port);
    }

    public FinishLynxClient(ProtocolConfiguration protocolConfiguration) {
        super(protocolConfiguration);
    }

    @Override
    public void handleEvent(Event event) {
        super.handleEvent(event);
        if(event instanceof ResultStartEvent){
            ResultStartEvent resultStartEvent = (ResultStartEvent) event;
            currentRaceId = resultStartEvent.getEventNumber() + "-" + resultStartEvent.getRoundNumber() + "-" + resultStartEvent.getHeatNumber();
            results.clear();
        }
        if(event instanceof ResultEvent){
            results.add((ResultEvent) event);
        }
        if(event instanceof ResultEndEvent){
            FullResultsEvent fullResultsEvent = new FullResultsEvent(currentRaceId, results);
            super.handleEvent(fullResultsEvent);
            results.clear();
        }
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        Event event = packet.handleData(new String(data));
        if (event != null) {
            handleEvent(event);
        }
    }

}
