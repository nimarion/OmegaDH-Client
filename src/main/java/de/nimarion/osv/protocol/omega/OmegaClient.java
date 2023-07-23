package de.nimarion.osv.protocol.omega;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.nimarion.osv.TCPClient;
import de.nimarion.osv.protocol.Event;
import de.nimarion.osv.protocol.Packet;
import de.nimarion.osv.protocol.omega.event.EndRankingEvent;
import de.nimarion.osv.protocol.omega.event.EnterRaceEvent;
import de.nimarion.osv.protocol.omega.event.ResultEvent;
import de.nimarion.osv.protocol.omega.event.ResultHundredsEvent;
import de.nimarion.osv.protocol.omega.event.ResultThousandsEvent;
import de.nimarion.osv.protocol.omega.event.StartRankingEvent;
import de.nimarion.osv.protocol.omega.event.SupplementaryInfoDataEvent;
import de.nimarion.osv.protocol.omega.event.SupplementaryInfoHeaderEvent;
import de.nimarion.osv.protocol.omega.packet.CurrentDaytimeCursorPacket;
import de.nimarion.osv.protocol.omega.packet.CurrentRacetimeCursorPacket;
import de.nimarion.osv.protocol.omega.packet.DeleteResultPacket;
import de.nimarion.osv.protocol.omega.packet.EndRankingPacket;
import de.nimarion.osv.protocol.omega.packet.EnterRacePacket;
import de.nimarion.osv.protocol.omega.packet.FalseStartPacket;
import de.nimarion.osv.protocol.omega.packet.NetTimePacket;
import de.nimarion.osv.protocol.omega.packet.ReactionTimePacket;
import de.nimarion.osv.protocol.omega.packet.RecoverFinishPacket;
import de.nimarion.osv.protocol.omega.packet.ResultHundredsPacket;
import de.nimarion.osv.protocol.omega.packet.ResultIntermediatePacket;
import de.nimarion.osv.protocol.omega.packet.ResultThousandsPacket;
import de.nimarion.osv.protocol.omega.packet.ResultsOfficalPacket;
import de.nimarion.osv.protocol.omega.packet.StartRankingPacket;
import de.nimarion.osv.protocol.omega.packet.StartedPacket;
import de.nimarion.osv.protocol.omega.packet.SupplementaryInfoDataPacket;
import de.nimarion.osv.protocol.omega.packet.SupplementaryInfoHeaderPacket;
import de.nimarion.osv.protocol.omega.packet.TemporaryResultPacket;
import de.nimarion.osv.protocol.omega.packet.TimeTrialStartedPacket;
import de.nimarion.osv.protocol.omega.packet.WindPacket;

public class OmegaClient extends TCPClient {

    private final List<String> shortcuts = Arrays
            .asList(new String[] { "DNS", "No Time", "DNF", "DQ", "USER1", "USER2", "USER3" });
    private String currentRaceId = null;
    private boolean rankingStarted = false;
    private Map<Integer, String> bibTimeHundreds = new HashMap<>();

    public OmegaClient(String host, int port) {
        super(host, port);
        registerPacket(new EnterRacePacket());
        registerPacket(new DeleteResultPacket());
        registerPacket(new StartRankingPacket());
        registerPacket(new EndRankingPacket());
        registerPacket(new CurrentDaytimeCursorPacket());
        registerPacket(new CurrentRacetimeCursorPacket());
        registerPacket(new WindPacket());
        registerPacket(new SupplementaryInfoHeaderPacket());
        registerPacket(new SupplementaryInfoDataPacket());
        registerPacket(new ReactionTimePacket());
        registerPacket(new ResultThousandsPacket());
        registerPacket(new ResultHundredsPacket());
        registerPacket(new TemporaryResultPacket());
        registerPacket(new ResultIntermediatePacket());
        registerPacket(new ResultsOfficalPacket());
        registerPacket(new StartedPacket());
        registerPacket(new NetTimePacket());
        registerPacket(new RecoverFinishPacket());
        registerPacket(new FalseStartPacket());
        registerPacket(new TimeTrialStartedPacket());
    }

    @Override
    public void handleEvent(Event event) {
        if (event instanceof EnterRaceEvent) {
            EnterRaceEvent enterRaceEvent = (EnterRaceEvent) event;
            currentRaceId = enterRaceEvent.getRaceId();
        }
        if (event instanceof EndRankingEvent) {
            currentRaceId = null;
            bibTimeHundreds.clear();
            rankingStarted = false;
        }
        if (event instanceof StartRankingEvent) {
            rankingStarted = true;
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

        // Results
        if (event instanceof ResultHundredsEvent) {
            ResultHundredsEvent resultHundredsEvent = (ResultHundredsEvent) event;
            if (shortcuts.contains(resultHundredsEvent.getTime())) {
                ResultEvent resultEvent = new ResultEvent(resultHundredsEvent.getRank(), resultHundredsEvent.getBib(),
                        resultHundredsEvent.getLane(), resultHundredsEvent.getTime(), null);
                super.handleEvent(resultEvent);
                return;
            }
            bibTimeHundreds.put(resultHundredsEvent.getBib(), resultHundredsEvent.getTime());
            return;
        }
        if (event instanceof ResultThousandsEvent) {
            ResultThousandsEvent resultThousandsEvent = (ResultThousandsEvent) event;
            if (bibTimeHundreds.containsKey(resultThousandsEvent.getBib())) {
                String hundreds = bibTimeHundreds.get(resultThousandsEvent.getBib());
                String thousands = hundreds.substring(0, hundreds.lastIndexOf(".") + 1)
                        + resultThousandsEvent.getTime();
                ResultEvent resultEvent = new ResultEvent(resultThousandsEvent.getRank(), resultThousandsEvent.getBib(),
                        resultThousandsEvent.getLane(), hundreds, thousands);
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
