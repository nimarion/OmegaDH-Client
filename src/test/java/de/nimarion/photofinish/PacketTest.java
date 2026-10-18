package de.nimarion.photofinish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import de.nimarion.photofinish.common.result.FullResultsEvent;
import de.nimarion.photofinish.common.result.ResultEndEvent;
import de.nimarion.photofinish.common.result.ResultEvent;
import de.nimarion.photofinish.common.result.ResultStartEvent;
import de.nimarion.photofinish.lynx.scoreboard.FinishLynxClient;
import de.nimarion.photofinish.lynx.scoreboard.packet.ResultHeaderPacket;
import de.nimarion.photofinish.lynx.scoreboard.packet.ResultLinePacket;
import de.nimarion.photofinish.lynx.scoreboard.packet.RunningTimePacket;
import de.nimarion.photofinish.osv.Event;
import de.nimarion.photofinish.osv.frontcamera.packet.EnteredFrontcameraPacket;
import de.nimarion.photofinish.osv.gemini.event.RunningTimeEvent;
import de.nimarion.photofinish.osv.gemini.packet.NetTimePacket;
import de.nimarion.photofinish.osv.omega.OmegaClient;
import de.nimarion.photofinish.osv.omega.event.EnterRaceEvent;
import de.nimarion.photofinish.osv.omega.event.ReactionTimeEvent;
import de.nimarion.photofinish.osv.omega.event.ResultHundredsEvent;
import de.nimarion.photofinish.osv.omega.event.StartRankingEvent;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoDataEvent;
import de.nimarion.photofinish.osv.omega.event.SupplementaryInfoHeaderEvent;
import de.nimarion.photofinish.osv.omega.event.WindEvent;
import de.nimarion.photofinish.osv.omega.packet.EnterRacePacket;
import de.nimarion.photofinish.osv.omega.packet.ResultHundredsPacket;
import de.nimarion.photofinish.osv.omega.packet.SupplementaryInfoDataPacket;
import de.nimarion.photofinish.osv.omega.packet.WindPacket;
import de.nimarion.photofinish.osv.sirocco.SiroccoWindEvent;

class PacketTest {

    @Test
    void testOmegaEnterRacePacket() {
        EnterRacePacket packet = new EnterRacePacket();
        byte[] raw = "\u0001\u0013DVSNR\u000203901001\u0004".getBytes(StandardCharsets.US_ASCII);
        assertTrue(packet.isPacket(raw));

        Event event = packet.handleData("03901001 ");
        EnterRaceEvent enterRace = assertInstanceOf(EnterRaceEvent.class, event);
        assertEquals("03901001", enterRace.getRaceId());
    }

    @Test
    void testOmegaResultHundredsPacket() {
        ResultHundredsPacket packet = new ResultHundredsPacket();
        byte[] raw = "\u0001\u0013DVRCC\u0002  1  643 6   12.80\u0004".getBytes(StandardCharsets.US_ASCII);
        assertTrue(packet.isPacket(raw));

        Event event = packet.handleData("  1  643 6   12.80");
        ResultHundredsEvent result = assertInstanceOf(ResultHundredsEvent.class, event);
        assertEquals(1, result.getRank());
        assertEquals(643, result.getBib());
        assertEquals(6, result.getLane());
        assertEquals("12.80", result.getTime());
    }

    @Test
    void testOmegaWindPacketValidAndInvalid() {
        WindPacket packet = new WindPacket();
        byte[] raw = "\u0001\u0013DVRWM\u0002 +1.2 m/s\u0004".getBytes(StandardCharsets.US_ASCII);
        assertTrue(packet.isPacket(raw));

        WindEvent valid = assertInstanceOf(WindEvent.class, packet.handleData(" +1.2 m/s"));
        assertEquals(1.2, valid.getWind(), 0.001);
        assertEquals("m/s", valid.getUnit());

        WindEvent noWind = assertInstanceOf(WindEvent.class, packet.handleData("      m/s"));
        assertNull(noWind.getWind());
        assertEquals("m/s", noWind.getUnit());
    }

    @Test
    void testSiroccoWindPacket() {
        de.nimarion.photofinish.osv.sirocco.WindPacket packet = new de.nimarion.photofinish.osv.sirocco.WindPacket();

        SiroccoWindEvent valid = assertInstanceOf(SiroccoWindEvent.class, packet.handleData("W,+1.44,M/S"));
        assertEquals(1.4f, valid.getWind(), 0.001f);

        assertNull(packet.handleData("W,invalid,M/S"));
    }

    @Test
    void testFinishLynxResultHeaderPacket() {
        ResultHeaderPacket packet = new ResultHeaderPacket();
        byte[] headerRaw = new byte[] { 1, 76, 72, 2, 65, 4 };
        byte[] startlistRaw = new byte[] { 1, 83, 72, 2, 65, 4 };
        byte[] otherRaw = new byte[] { 1, 76, 82, 2, 65, 4 };

        assertTrue(packet.isPacket(headerRaw));
        assertTrue(packet.isPacket(startlistRaw));
        assertFalse(packet.isPacket(otherRaw));
        assertFalse(packet.isPacket(new byte[] { 1, 76 }));

        String payload = "\u0001LH\u0002OFFICIAL|100m Men|+0.5|17|2|1|F|8\u0004";
        ResultStartEvent event = assertInstanceOf(ResultStartEvent.class, packet.handleData(payload));
        assertEquals("17-2-1", event.getId());
    }

    @Test
    void testFinishLynxResultLinePacket() {
        ResultLinePacket packet = new ResultLinePacket();
        byte[] lineRaw = new byte[] { 1, 76, 82, 2, 65, 4 };
        assertTrue(packet.isPacket(lineRaw));
        assertFalse(packet.isPacket(new byte[] { 1, 76 }));

        String payload = "\u0001LR\u00021|6|643|Vaughn|TEXAS|12.805||||||0.162\u0004";
        ResultEvent event = assertInstanceOf(ResultEvent.class, packet.handleData(payload));
        assertEquals(1, event.getPlace());
        assertEquals(6, event.getLane());
        assertEquals(643, event.getId());
        assertEquals("12.805", event.getTime());
        assertEquals("12.805", event.getTimeThousands());
        assertEquals("0.162", event.getReactionTime());

        String emptyTimePayload = "\u0001LR\u00021|6|643|Vaughn|TEXAS|||||||0.162\u0004";
        assertNull(packet.handleData(emptyTimePayload));
    }

    @Test
    void testFinishLynxRunningTimePacket() {
        RunningTimePacket packet = new RunningTimePacket();
        byte[] raw = new byte[] { 1, 84, 82, 2, 49, 50, 4 };
        assertTrue(packet.isPacket(raw));

        String payload = "\u0001TR\u0002  12.34 \u0004";
        RunningTimeEvent event = assertInstanceOf(RunningTimeEvent.class, packet.handleData(payload));
        assertEquals("12.34", event.getTime());
    }

    @Test
    void testShortFramesDoNotThrowOutOfBounds() {
        byte[] shortFrame = new byte[] { 1, 20, 83, 48 };
        assertFalse(new EnterRacePacket().isPacket(shortFrame));
        assertFalse(new EnteredFrontcameraPacket().isPacket(new byte[] { 1, 2 }));
        assertFalse(new de.nimarion.photofinish.osv.gemini.packet.ResultLinePacket().isPacket(shortFrame));
        assertFalse(new NetTimePacket().isPacket(shortFrame));
    }

    @Test
    void testOmegaClientSingleDispatchAndCp1252Handling() {
        OmegaClient client = new OmegaClient("localhost", 0);
        List<Event> received = new ArrayList<>();
        client.addEventHandler(new EventHandler() {
            @Override
            public void handleEvent(Event event) {
                received.add(event);
            }
        });

        client.handleEvent(new EnterRaceEvent("03901001"));
        client.handleEvent(new StartRankingEvent(8));

        // SupplementaryInfoDataEvent without header should not throw NPE
        client.handleEvent(new SupplementaryInfoDataEvent("test"));

        // Cp1252 payload with umlauts in fixed-width supplementary fields
        client.handleEvent(new SupplementaryInfoHeaderEvent(List.of("CLUB"), List.of(16)));
        byte[] rawPacket = "\u0001\u0013DVSCD\u0002Neuköllner SF   \u0004\u0000"
                .getBytes(Charset.forName("Cp1252"));
        client.handleData(new SupplementaryInfoDataPacket(), rawPacket);

        SupplementaryInfoDataEvent suppEvent = received.stream()
                .filter(SupplementaryInfoDataEvent.class::isInstance)
                .map(SupplementaryInfoDataEvent.class::cast)
                .findFirst()
                .orElseThrow();
        assertEquals("Neuköllner SF", suppEvent.getDataMap().get("CLUB"));

        // ReactionTimeEvent should dispatch ResultEvent exactly once
        client.handleEvent(new ResultHundredsEvent(1, 643, 6, "12.80"));
        client.handleEvent(new ReactionTimeEvent(643, 6, "0.162", " "));

        long resultEventCount = received.stream().filter(ResultEvent.class::isInstance).count();
        assertEquals(1, resultEventCount);
    }

    @Test
    void testFinishLynxClientPreservesFullResultsList() {
        FinishLynxClient client = new FinishLynxClient("localhost", 0);
        List<FullResultsEvent> fullResultsEvents = new ArrayList<>();
        client.addEventHandler(new EventHandler() {
            @Override
            public void handleEvent(Event event) {
                if (event instanceof FullResultsEvent fullResultsEvent) {
                    fullResultsEvents.add(fullResultsEvent);
                }
            }
        });

        client.handleEvent(new ResultStartEvent("17-2-1"));
        client.handleEvent(new ResultEvent(1, 6, 643, "12.80", "12.800", "0.162"));
        client.handleEvent(new ResultEndEvent());

        assertEquals(1, fullResultsEvents.size());
        assertEquals(1, fullResultsEvents.get(0).getResults().size());
    }

    @Test
    void testConcurrentEventHandlerModificationAndClientClose() throws Exception {
        try (java.net.ServerSocket serverSocket = new java.net.ServerSocket(0)) {
            OmegaClient client = new OmegaClient("127.0.0.1", serverSocket.getLocalPort());
            List<Event> received = new ArrayList<>();
            EventHandler selfRemovingHandler = new EventHandler() {
                @Override
                public void handleEvent(Event event) {
                    client.removeEventHandler(this);
                    received.add(event);
                }
            };
            client.addEventHandler(selfRemovingHandler);
            client.addEventHandler(new EventHandler() {
                @Override
                public void handleEvent(Event event) {
                    received.add(event);
                }
            });

            client.handleEvent(new EnterRaceEvent("001"));
            assertEquals(2, received.size());

            client.start();
            try (java.net.Socket accepted = serverSocket.accept()) {
                assertTrue(client.isActive());
                client.close();
                client.join(2000);
                assertFalse(client.isActive());
                assertFalse(client.isAlive());
            }
        }
    }
}
