package de.nimarion.osv.protocol.startlist;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

public class Startlist {

    private List<String[]> startlist;

    public Startlist(String path) throws Exception {
        startlist = new ArrayList<>();
        String fileName = path;
        
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(fileName), "Cp1252")).withCSVParser(parser).build()) {
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
