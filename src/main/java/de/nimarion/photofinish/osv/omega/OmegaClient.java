package de.nimarion.photofinish.osv.omega;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.nimarion.photofinish.TCPClient;
import de.nimarion.photofinish.common.result.FullResultsEvent;
import de.nimarion.photofinish.common.result.ResultEndEvent;
import de.nimarion.photofinish.common.result.ResultEvent;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.Packet;
import de.nimarion.photofinish.osv.ProtocolConfiguration;
import de.nimarion.photofinish.osv.omega.event.EnterRaceEvent;
import de.nimarion.photofinish.osv.omega.event.ReactionTimeEvent;
import de.nimarion.photofinish.osv.omega.event.ResultHundredsEvent;
import de.nimarion.photofinish.osv.omega.event.ResultThousandsEvent;
import de.nimarion.photofinish.osv.omega.event.StartRankingEvent;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoDataEvent;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoHeaderEvent;
import de.nimarion.photofinish.osv.omega.packet.CurrentDaytimeCursorPacket;
import de.nimarion.photofinish.osv.omega.packet.CurrentRacetimeCursorPacket;
import de.nimarion.photofinish.osv.omega.packet.DeleteResultPacket;
import de.nimarion.photofinish.osv.omega.packet.EndRankingPacket;
import de.nimarion.photofinish.osv.omega.packet.EnterRacePacket;
import de.nimarion.photofinish.osv.omega.packet.FalseStartPacket;
import de.nimarion.photofinish.osv.omega.packet.NetTimePacket;
import de.nimarion.photofinish.osv.omega.packet.ReactionTimePacket;
import de.nimarion.photofinish.osv.omega.packet.RecoverFinishPacket;
import de.nimarion.photofinish.osv.omega.packet.ResultHundredsPacket;
import de.nimarion.photofinish.osv.omega.packet.ResultIntermediatePacket;
import de.nimarion.photofinish.osv.omega.packet.ResultThousandsPacket;
import de.nimarion.photofinish.osv.omega.packet.ResultsOfficalPacket;
import de.nimarion.photofinish.osv.omega.packet.StartRankingPacket;
import de.nimarion.photofinish.osv.omega.packet.StartedPacket;
import de.nimarion.photofinish.osv.omega.packet.SupplementaryInfoDataPacket;
import de.nimarion.photofinish.osv.omega.packet.SupplementaryInfoHeaderPacket;
import de.nimarion.photofinish.osv.omega.packet.TemporaryResultPacket;
import de.nimarion.photofinish.osv.omega.packet.TimeTrialStartedPacket;
import de.nimarion.photofinish.osv.omega.packet.WindPacket;

public class OmegaClient extends TCPClient {

    private final List<String> shortcuts = Arrays
            .asList(new String[] { "DNS", "No Time", "DNF", "DQ", "USER1", "USER2", "USER3" });
    private String currentRaceId = null;
    private boolean rankingStarted = false;
    private Map<Integer, ResultEvent> bibResult = new HashMap<>();
    private SupplementaryInfoHeaderEvent lastSupplementaryInfoHeader;

    public OmegaClient(String host, int port) {
        super(host, port);
        registerPacket(new CurrentDaytimeCursorPacket());
        registerPacket(new CurrentRacetimeCursorPacket());
        registerPacket(new DeleteResultPacket());
        registerPacket(new EndRankingPacket());
        registerPacket(new EnterRacePacket());
        registerPacket(new FalseStartPacket());
        registerPacket(new NetTimePacket());
        registerPacket(new ReactionTimePacket());
        registerPacket(new RecoverFinishPacket());
        registerPacket(new ResultHundredsPacket());
        registerPacket(new ResultIntermediatePacket());
        registerPacket(new ResultThousandsPacket());
        registerPacket(new ResultsOfficalPacket());
        registerPacket(new StartRankingPacket());
        registerPacket(new StartedPacket());
        registerPacket(new SupplementaryInfoDataPacket());
        registerPacket(new SupplementaryInfoHeaderPacket());
        registerPacket(new TemporaryResultPacket());
        registerPacket(new TimeTrialStartedPacket());
        registerPacket(new WindPacket());
    }

    public OmegaClient(ProtocolConfiguration protocolConfiguration) {
        this(protocolConfiguration.getIp(), protocolConfiguration.getPort());
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EnterRaceEvent) {
            EnterRaceEvent enterRaceEvent = (EnterRaceEvent) event;
            currentRaceId = enterRaceEvent.getRaceId();
        }
        if (event instanceof ResultEndEvent) {
            FullResultsEvent fullResultsEvent = new FullResultsEvent(currentRaceId, List.copyOf(bibResult.values()));
            super.handleEvent(fullResultsEvent);
            super.handleEvent(event);
            bibResult.clear();
            rankingStarted = false;
            currentRaceId = null;
            lastSupplementaryInfoHeader = null;
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
            if (lastSupplementaryInfoHeader == null) {
                return;
            }
            SupplementaryInfoDataEvent supplementaryInfoDataEvent = (SupplementaryInfoDataEvent) event;
            String dataString = supplementaryInfoDataEvent.getDataString();
            int lastIndex = 0;
            for (int i = 0; i < lastSupplementaryInfoHeader.getFields().size(); i++) {
                String field = lastSupplementaryInfoHeader.getFields().get(i);
                int length = lastSupplementaryInfoHeader.getLengths().get(i);
                int endIndex = Math.min(lastIndex + length - 1, dataString.length());
                if (lastIndex >= endIndex) {
                    break;
                }
                String value = dataString.substring(lastIndex, endIndex).trim();
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
        if (event instanceof ReactionTimeEvent) {
            ReactionTimeEvent reactionTimeEvent = (ReactionTimeEvent) event;
            if (bibResult.containsKey(reactionTimeEvent.getBib())) {
                if (reactionTimeEvent.getReactionTime() != null) {
                    bibResult.get(reactionTimeEvent.getBib()).setReactionTime(reactionTimeEvent.getReactionTime());
                }
                super.handleEvent(bibResult.get(reactionTimeEvent.getBib()));
            }
            return;
        }
    }

    @Override
    public void handleData(Packet packet, byte[] data) {
        handleFramedData(packet, data);
    }

}
