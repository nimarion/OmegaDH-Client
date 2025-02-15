package de.nimarion.photofinish.lynx.startlist;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import de.nimarion.photofinish.common.startlist.Competitor;
import de.nimarion.photofinish.common.startlist.Startlist;
import de.nimarion.photofinish.common.startlist.StartlistParser;
import de.nimarion.photofinish.lynx.scoreboard.FinishLynxClient;

public class LynxStartlistParser extends StartlistParser {

    private List<String[]> csvData;

    public LynxStartlistParser(String path) {
        super(path);
    }

    private Startlist forEventCode(int eventNumber, int roundNumber, int heatNumber) {
        Startlist startlistEvent = null;
        for (String[] arrays : csvData) {

            if (!arrays[0].isEmpty()) {
                if (Integer.parseInt(arrays[0]) == eventNumber && Integer.parseInt(arrays[1]) == roundNumber
                        && Integer.parseInt(arrays[2]) == heatNumber) {
                    int distance = Integer.parseInt(arrays[9]);
                    String eventName = arrays[3];
                    startlistEvent = new Startlist(eventNumber + "-" + roundNumber + "-" + heatNumber, distance,
                            eventName);
                    continue;
                }
                continue;
            }

            if (startlistEvent == null) {
                continue;
            }
            if (Arrays.stream(arrays).allMatch(String::isBlank)) {
                break;
            }

            String lane = arrays[2];
            String bib = arrays[1];
            String lastname = arrays[4];
            String firstname = arrays[5];
            String nation = arrays[5];
            Competitor member = new Competitor(firstname, lastname, Integer.parseInt(lane),
                    Integer.parseInt(bib), nation);

            startlistEvent.addCompetitor(member);
        }
        return startlistEvent;
    }

    private List<StartlistEvent> getAllEvents() {
        return csvData.stream()
                .filter(arrays -> !arrays[0].isEmpty())
                .map(arrays -> new StartlistEvent(
                        Integer.parseInt(arrays[0]),
                        Integer.parseInt(arrays[1]),
                        Integer.parseInt(arrays[2])))
                .collect(Collectors.toList());
    }

    public Startlist getStartlist(int eventNumber, int roundNumber, int heatNumber) {
        return getStartlists().stream()
                .filter(s -> FinishLynxClient.getEventNumberFromId(s.getId()) == eventNumber &&
                        FinishLynxClient.getRoundNumberFromId(s.getId()) == roundNumber &&
                        FinishLynxClient.getHeatNumberFromId(s.getId()) == heatNumber)
                .findFirst()
                .orElse(null);
    }

    private class StartlistEvent {

        private int eventNumber;
        private int roundNumber;
        private int heatNumber;

        public StartlistEvent(int eventNumber, int roundNumber, int heatNumber) {
            this.eventNumber = eventNumber;
            this.roundNumber = roundNumber;
            this.heatNumber = heatNumber;
        }

        public int getEventNumber() {
            return eventNumber;
        }

        public int getRoundNumber() {
            return roundNumber;
        }

        public int getHeatNumber() {
            return heatNumber;
        }
    }

    @Override
    protected List<Startlist> parse(String path) {
        List<Startlist> startlists = new ArrayList<>();
        csvData = new ArrayList<>();
        String fileName = path;

        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(fileName), "Cp1252"))
                .withCSVParser(parser).build()) {
            csvData = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        for (StartlistEvent event : getAllEvents()) {
            startlists.add(forEventCode(event.getEventNumber(), event.getRoundNumber(), event.getHeatNumber()));
        }
        return startlists;
    }

}
