package de.nimarion.photofinish.osv.startlist;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(fileName), "Cp1252"))
                .withCSVParser(parser).withSkipLines(1).build()) {
           csvData = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        for (String event : getAllEventCodes()) {
            startlists.add(forEventCode(event));
        }
    }

    private Startlist forEventCode(String eCode) {
        Startlist startlistEvent = null;
        for (String[] arrays : csvData) {
            String eventCode = arrays[0];
            String date = arrays[1];
            String startTime = arrays[2];

            String length = arrays[8];
            String title = arrays[9];
            if (arrays[0].equals(eCode)) {
                startlistEvent = new Startlist(eventCode, Integer.parseInt(length), title, date, startTime);
                continue;
            }
            if (startlistEvent == null) {
                continue;
            }
            if (!arrays[0].isEmpty()) {
                break;
            }
            String lane = arrays[3];
            String bib = arrays[4];
            String lastname = arrays[5];
            String firstname = arrays[6];
            String nation = arrays[7];
            Competitor member = new Competitor(firstname, lastname, Integer.parseInt(lane),
                    Integer.parseInt(bib), nation);

            startlistEvent.addCompetitor(member);
        }
        return startlistEvent;
    }

    public String[] getAllEventCodes() {
        List<String> events = new ArrayList<>();
        for (String[] arrays : csvData) {
            if (!arrays[0].isEmpty()) {
                System.out.println("-> " + arrays[0]);
                events.add(arrays[0]);
            }
        }
        return events.toArray(new String[0]);
    }

    public List<Startlist> getStartlists() {
        return startlists;
    }

    public Startlist getStartlist(String id) {
        for (Startlist startlist : startlists) {
            if (startlist.getId().equals(id)) {
                return startlist;
            }
        }
        return null;
    }

    public List<String[]> getCsvData() {
        return csvData;
    }

}
