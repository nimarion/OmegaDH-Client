package de.nimarion.photofinish.osv.omega;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.common.result.ResultEvent;
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
    private Map<Integer, ResultEvent> bibResult = new HashMap<>();
    private SupplementaryInfoHeaderEvent lastSupplementaryInfoHeader;

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
            FullResultsEvent fullResultsEvent = new FullResultsEvent(currentRaceId, bibResult.values().stream().toList());
            super.handleEvent(fullResultsEvent);
            bibResult.clear();
            rankingStarted = false;
            currentRaceId = null;
            return;
        }
        if (event instanceof StartRankingEvent) {
            rankingStarted = true;
        }

        if (!(event instanceof ResultHundredsEvent) && !(event instanceof ResultThousandsEvent)
                && !(event instanceof SupplementaryInfoDataEvent) && !(event instanceof SupplementaryInfoHeaderEvent) && !(event instanceof ReactionTimeEvent)) {
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

        // For FullResultsEvent
        if (event instanceof ResultHundredsEvent) {
            ResultHundredsEvent resultHundredsEvent = (ResultHundredsEvent) event;
            if (shortcuts.contains(resultHundredsEvent.getTime())) {
                return;
            }
            ResultEvent resultEvent = new ResultEvent(resultHundredsEvent.getRank(), resultHundredsEvent.getLane(),
            resultHundredsEvent.getBib(),
            resultHundredsEvent.getTime(), null, null);
            bibResult.put(resultHundredsEvent.getBib(), resultEvent);
            return;
        }
        if (event instanceof ResultThousandsEvent) {
            ResultThousandsEvent resultThousandsEvent = (ResultThousandsEvent) event;
            if (bibResult.containsKey(resultThousandsEvent.getBib())) {
                String hundreds = bibResult.get(resultThousandsEvent.getBib()).getTime();
                String thousands = hundreds.substring(0, hundreds.lastIndexOf(".") + 1)
                        + resultThousandsEvent.getTime();
                bibResult.get(resultThousandsEvent.getBib()).setTimeThousands(thousands);
            }
            return;
        }
        if(event instanceof ReactionTimeEvent){
            
            ReactionTimeEvent reactionTimeEvent = (ReactionTimeEvent) event;
            if (bibResult.containsKey(reactionTimeEvent.getBib())) {
                if(reactionTimeEvent.getReactionTime() != null){
                    bibResult.get(reactionTimeEvent.getBib()).setReactionTime(reactionTimeEvent.getReactionTime());
                }
                super.handleEvent(bibResult.get(reactionTimeEvent.getBib()));
                handleEvent(bibResult.get(reactionTimeEvent.getBib()));
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
