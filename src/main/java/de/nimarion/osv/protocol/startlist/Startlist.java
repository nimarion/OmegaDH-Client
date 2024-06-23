package de.nimarion.osv.protocol.startlist;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Startlist {

    private List<String[]> startlist;

    public Startlist(String path) throws Exception {
        startlist = new ArrayList<>();
        String fileName = path;
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(fileName)).withCSVParser(parser).build()) {
            startlist = reader.readAll();
        }
    }

    public StartlistEvent forEventCode(String eventCode) {
        StartlistEvent startlistEvent = null;
        for (String[] arrays : startlist) {
            if (arrays[0].equals(eventCode)) {
                startlistEvent = new StartlistEvent(eventCode, Integer.parseInt(arrays[8]), arrays[9]);
                continue;
            }
            if (startlistEvent == null) {
                continue;
            }
            if (!arrays[0].isEmpty()) {
                break;
            }
            StartlistMember member = new StartlistMember(arrays[6], arrays[5], Integer.parseInt(arrays[3]),
                    Integer.parseInt(arrays[4]), arrays[7]);

            startlistEvent.addMember(member);
        }
        return startlistEvent;
    }

}
