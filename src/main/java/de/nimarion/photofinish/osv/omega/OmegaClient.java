package de.nimarion.photofinish.osv.omega;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.common.ResultEvent;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;
import de.nimarion.photofinish.osv.omega.event.EndRankingEvent;
import de.nimarion.photofinish.osv.omega.event.EnterRaceEvent;
import de.nimarion.photofinish.osv.omega.event.FullResultsEvent;
import de.nimarion.photofinish.osv.omega.event.ReactionTimeEvent;
import de.nimarion.photofinish.osv.omega.event.ResultHundredsEvent;
import de.nimarion.photofinish.osv.omega.event.ResultThousandsEvent;
import de.nimarion.photofinish.osv.omega.event.StartRankingEvent;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoDataEvent;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoHeaderEvent;

public class OmegaClient extends TCPClient {

    private final List<String> shortcuts = Arrays
            .asList(new String[] { "DNS", "No Time", "DNF", "DQ", "USER1", "USER2", "USER3" });
    private String currentRaceId = null;
    private boolean rankingStarted = false;
    private Map<Integer, String> bibTimeHundreds = new HashMap<>();
    private SupplementaryInfoHeaderEvent lastSupplementaryInfoHeader;
    private List<ResultEvent> results = new ArrayList<>();
    private List<ReactionTimeEvent> reactionTimes = new ArrayList<>();

    public OmegaClient(String host, int port) {
        super(host, port);
    }

    public OmegaClient(ProtocolConfiguration protocolConfiguration) {
        super(protocolConfiguration);
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EnterRaceEvent) {
            EnterRaceEvent enterRaceEvent = (EnterRaceEvent) event;
            currentRaceId = enterRaceEvent.getRaceId();
        }
        if (event instanceof EndRankingEvent) {
            bibTimeHundreds.clear();
            rankingStarted = false;
            FullResultsEvent fullResultsEvent = new FullResultsEvent(currentRaceId, results,reactionTimes);
            super.handleEvent(fullResultsEvent);
            results.clear();
            reactionTimes.clear();
            currentRaceId = null;
            return;
        }
        if (event instanceof StartRankingEvent) {
            rankingStarted = true;
            results.clear();
            reactionTimes.clear();
        }
        if (event instanceof ResultEvent) {
            results.add((ResultEvent) event);
        }
        if(event instanceof ReactionTimeEvent){
            reactionTimes.add((ReactionTimeEvent) event);
        }

        if (!(event instanceof ResultHundredsEvent) && !(event instanceof ResultThousandsEvent)
                && !(event instanceof SupplementaryInfoDataEvent) && !(event instanceof SupplementaryInfoHeaderEvent)) {
            super.handleEvent(event);
            return;
        }
        if (currentRaceId == null || !rankingStarted) {
            super.handleEvent(event);
            return;
        }

        if (event instanceof SupplementaryInfoHeaderEvent) {
            lastSupplementaryInfoHeader = (SupplementaryInfoHeaderEvent) event;
            return;
        } else if (event instanceof SupplementaryInfoDataEvent) {
            SupplementaryInfoDataEvent supplementaryInfoDataEvent = (SupplementaryInfoDataEvent) event;
            int lastIndex = 0;
            for (int i = 0; i < lastSupplementaryInfoHeader.getFields().size(); i++) {
                String field = lastSupplementaryInfoHeader.getFields().get(i);
                int length = lastSupplementaryInfoHeader.getLengths().get(i);
                String value = supplementaryInfoDataEvent.getDataString().substring(lastIndex, lastIndex + length - 1)
                        .trim();
                lastIndex += length;
                supplementaryInfoDataEvent.addData(field, value);
            }
            super.handleEvent(supplementaryInfoDataEvent);
            return;
        }

        // Results
        if (event instanceof ResultHundredsEvent) {
            ResultHundredsEvent resultHundredsEvent = (ResultHundredsEvent) event;
            if (shortcuts.contains(resultHundredsEvent.getTime())) {
                ResultEvent resultEvent = new ResultEvent(resultHundredsEvent.getRank(), resultHundredsEvent.getLane(),
                        resultHundredsEvent.getBib(),
                        resultHundredsEvent.getTime(), null, null);
                handleEvent(resultEvent);
                super.handleEvent(resultEvent);
                return;
            }
            bibTimeHundreds.put(resultHundredsEvent.getBib(), resultHundredsEvent.getTime());
            return;
        }
        // TODO: Integrate Reaction Time
        if (event instanceof ResultThousandsEvent) {
            ResultThousandsEvent resultThousandsEvent = (ResultThousandsEvent) event;
            if (bibTimeHundreds.containsKey(resultThousandsEvent.getBib())) {
                String hundreds = bibTimeHundreds.get(resultThousandsEvent.getBib());
                String thousands = hundreds.substring(0, hundreds.lastIndexOf(".") + 1)
                        + resultThousandsEvent.getTime();
                ResultEvent resultEvent = new ResultEvent(resultThousandsEvent.getRank(),
                        resultThousandsEvent.getLane(), resultThousandsEvent.getBib(), hundreds, thousands,
                        null);
                handleEvent(resultEvent);
                super.handleEvent(resultEvent);
            }
            return;
        }
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        Event event;
        if (data.length <= 9) {
            event = packet.handleData(null);
        } else {
            byte[] information = new byte[data.length - 10];
            System.arraycopy(data, 8, information, 0, data.length - 10);
            event = packet.handleData(new String(information));
        }
        if (event != null) {
            handleEvent(event);
        }
    }

}
