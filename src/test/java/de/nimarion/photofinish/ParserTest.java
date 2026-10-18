package de.nimarion.photofinish;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import de.nimarion.photofinish.common.startlist.Competitor;
import de.nimarion.photofinish.common.startlist.Startlist;
import de.nimarion.photofinish.lynx.result.LynxEvent;
import de.nimarion.photofinish.lynx.result.LynxResult;
import de.nimarion.photofinish.lynx.result.LynxResultParser;
import de.nimarion.photofinish.lynx.result.LynxResults;
import de.nimarion.photofinish.lynx.startlist.LynxStartlistParser;
import de.nimarion.photofinish.osv.startlist.OsvStartlist;
import de.nimarion.photofinish.osv.startlist.OsvStartlistParser;
import de.nimarion.photofinish.taf.BasicAthlete;
import de.nimarion.photofinish.taf.BasicAthleteListParser;

class ParserTest {

    @TempDir
    Path tempDir;

    @Test
    void testLynxResultParserHurdlesWithWind() {
        LynxResults results = LynxResultParser.parse("data/lynx/017-2-01.lif");

        assertNotNull(results);
        LynxEvent event = results.getEvent();
        assertEquals("17-2-1", event.getId());
        assertEquals("100 Meter Hurdles Women", event.getEventName());
        assertEquals(0.6f, event.getWind(), 0.001f);
        assertEquals("m/s", event.getWindUnit());
        assertEquals("13:41:52.2875", event.getStartTime());
        assertEquals(1, results.getResults().size());

        LynxResult finisher = results.getResults().get(0);
        assertEquals(1, finisher.getPlace());
        assertEquals(643, finisher.getBib());
        assertEquals(6, finisher.getLane());
        assertEquals("Vaughn", finisher.getLastname());
        assertEquals("Angela", finisher.getFirstname());
        assertEquals("UNIVERSITY OF TEXAS", finisher.getAffilation());
        assertEquals("12.80", finisher.getTime());
        assertEquals("0.162", finisher.getReactionTime());
    }

    @Test
    void testLynxResultParserIndoor60mWithCp1252Umlauts() {
        LynxResults results = LynxResultParser.parse("data/lynx/31500017-1-02.lif");

        assertNotNull(results);
        LynxEvent event = results.getEvent();
        assertEquals("31500017-1-2", event.getId());
        assertEquals("11:02 | 60m W", event.getEventName());
        assertNull(event.getWind());
        assertEquals("60", event.getDistance());
        assertEquals("11:03:28.5250", event.getStartTime());
        assertEquals(8, results.getResults().size());

        LynxResult first = results.getResults().get(0);
        assertEquals(1, first.getPlace());
        assertEquals(2926, first.getBib());
        assertEquals(8, first.getLane());
        assertEquals("Ksenia", first.getFirstname());
        assertEquals("Helios", first.getLastname());
        assertEquals("SV GO! Saar 05", first.getAffilation());
        assertEquals("7.636", first.getTime());

        LynxResult third = results.getResults().get(2);
        assertEquals(3, third.getPlace());
        assertEquals("LAC Quelle Fürth", third.getAffilation());
    }

    @Test
    void testLynxResultParserSkipsDnfEntries() {
        LynxResults results = LynxResultParser.parse("data/lynx/32400031-1-11.lif");

        assertNotNull(results);
        assertEquals("32400031-1-11", results.getEvent().getId());
        assertEquals("800", results.getEvent().getDistance());
        assertEquals(10, results.getResults().size());
    }

    @Test
    void testLynxResultParserEmptyOrMissingFile() throws IOException {
        Path missingFile = tempDir.resolve("missing.lif");
        assertNull(LynxResultParser.parse(missingFile.toString()));

        Path emptyFile = Files.createFile(tempDir.resolve("empty.lif"));
        assertNull(LynxResultParser.parse(emptyFile.toString()));
    }

    @Test
    void testLynxStartlistParser() {
        LynxStartlistParser parser = new LynxStartlistParser("data/lynx/Lynx.evt");
        List<Startlist> startlists = parser.getStartlists();

        assertEquals(2, startlists.size());

        Startlist first = startlists.get(0);
        assertEquals("32600001-1-1", first.getId());
        assertEquals(100, first.getDistance());
        assertEquals(6, first.getCompetitors().size());

        Competitor comp = first.getCompetitors().get(0);
        assertEquals(361, comp.getBib());
        assertEquals(2, comp.getLane());
        assertEquals("Mahmoud Hafiz", comp.getFirstname());
        assertEquals("Ibrahim", comp.getLastname());
        assertEquals("Saudiarabien", comp.getNation());

        Startlist second = startlists.get(1);
        assertEquals("32600001-1-2", second.getId());
        assertEquals(6, second.getCompetitors().size());
    }

    @Test
    void testLynxStartlistParserEmptyOrMissingFile() throws IOException {
        Path missingFile = tempDir.resolve("missing.evt");
        assertTrue(new LynxStartlistParser(missingFile.toString()).getStartlists().isEmpty());

        Path emptyFile = Files.createFile(tempDir.resolve("empty.evt"));
        assertTrue(new LynxStartlistParser(emptyFile.toString()).getStartlists().isEmpty());
    }

    @Test
    void testOsvStartlistParser() {
        OsvStartlistParser parser = new OsvStartlistParser("data/osv/startlist.csv");
        List<Startlist> startlists = parser.getStartlists();

        assertEquals(15, startlists.size());

        OsvStartlist first = assertInstanceOf(OsvStartlist.class, startlists.get(0));
        assertEquals("03901001", first.getId());
        assertEquals("22.06.2024", first.getDate());
        assertEquals("16:25", first.getStartTime());
        assertEquals(400, first.getDistance());
        assertEquals("400m WJU20 - Zeitlauf 1", first.getTitle());
        assertEquals(4, first.getCompetitors().size());

        Competitor comp = first.getCompetitors().get(0);
        assertEquals(20, comp.getBib());
        assertEquals(3, comp.getLane());
        assertEquals("Anouk", comp.getFirstname());
        assertEquals("Krause-Jentsch", comp.getLastname());
        assertEquals("GER | Neuköllner SF", comp.getNation());
    }

    @Test
    void testOsvStartlistParserEmptyOrMissingFile() throws IOException {
        Path missingFile = tempDir.resolve("missing.csv");
        assertTrue(new OsvStartlistParser(missingFile.toString()).getStartlists().isEmpty());

        Path emptyFile = Files.createFile(tempDir.resolve("empty.csv"));
        assertTrue(new OsvStartlistParser(emptyFile.toString()).getStartlists().isEmpty());
    }

    @Test
    void testBasicAthleteListParser() {
        List<BasicAthlete> athletes = BasicAthleteListParser.parse("data/taf/only_athlete_export.csv");

        assertTrue(athletes.size() > 100);

        BasicAthlete first = athletes.get(0);
        assertEquals(1, first.getBib());
        assertEquals("Malaika", first.getFirstName());
        assertEquals("Mihambo", first.getLastName());
        assertEquals("GER", first.getCountry());
    }

    @Test
    void testBasicAthleteListParserEmptyOrMissingFile() throws IOException {
        Path missingFile = tempDir.resolve("missing_taf.csv");
        assertTrue(BasicAthleteListParser.parse(missingFile.toString()).isEmpty());

        Path emptyFile = Files.createFile(tempDir.resolve("empty_taf.csv"));
        assertTrue(BasicAthleteListParser.parse(emptyFile.toString()).isEmpty());
    }
}
