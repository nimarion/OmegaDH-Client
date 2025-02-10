package de.nimarion.photofinish.lynx.startlist;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class StartlistParser {

    private List<String[]> csvData;
    private List<Startlist> startlists;

    public StartlistParser(String path) {
        startlists = new ArrayList<>();
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
    }

    private Startlist forEventCode(int eventNumber, int roundNumber, int heatNumber) {
        Startlist startlistEvent = null;
        for (String[] arrays : csvData) {
           
            if(!arrays[0].isEmpty()) {
                if(Integer.parseInt(arrays[0]) == eventNumber && Integer.parseInt(arrays[1]) == roundNumber && Integer.parseInt(arrays[2]) == heatNumber) {
                    int distance = Integer.parseInt(arrays[9]);
                    String eventName = arrays[3];
                    startlistEvent = new Startlist(eventNumber, roundNumber, heatNumber, distance, eventName, "", "");
                    continue;
                }
                continue;
            }
           
            if (startlistEvent == null) {
                continue;
            }
            if(Arrays.stream(arrays).allMatch(String::isBlank)){
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

    public List<StartlistEvent> getAllEvents() {
        List<StartlistEvent> events = new ArrayList<>();
        for (String[] arrays : csvData) {
            if (!arrays[0].isEmpty()) {
                events.add(new StartlistEvent(Integer.parseInt(arrays[0]), Integer.parseInt(arrays[1]),
                        Integer.parseInt(arrays[2])));
            }
        }
        return events;
    }

    public List<Startlist> getStartlists() {
        return startlists;
    }

    public Startlist getStartlist(int eventNumber, int roundNumber, int heatNumber) {
        for (Startlist startlist : startlists) {
            if (startlist.getEventNumber() == eventNumber && startlist.getRoundNumber() == roundNumber
                    && startlist.getHeatNumber() == heatNumber) {
                return startlist;
            }
        }
        return null;
    }

    public List<String[]> getCsvData() {
        return csvData;
    }

}
