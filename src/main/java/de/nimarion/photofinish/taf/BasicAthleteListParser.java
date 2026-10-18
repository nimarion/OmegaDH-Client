package de.nimarion.photofinish.taf;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicAthleteListParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicAthleteListParser.class);
    private static final Charset CP1252 = Charset.forName("Cp1252");

    public static List<BasicAthlete> parse(String path) {
        List<BasicAthlete> results = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), CP1252))
                .withCSVParser(parser).withSkipLines(1).build()) {
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (Arrays.stream(row).allMatch(String::isBlank)) {
                    break;
                }
                String bibStr = getFromArray(1, row);
                Integer bib = bibStr != null ? Integer.parseInt(bibStr) : null;
                String firstname = getFromArray(3, row);
                String lastname = getFromArray(4, row);
                String country = getFromArray(11, row);
                LOGGER.debug("Bib: {}, Firstname: {}, Lastname: {}, Country: {}", bib, firstname, lastname, country);

                BasicAthlete basicAthlete = new BasicAthlete(bib, firstname, lastname, country);
                results.add(basicAthlete);
            }
        } catch (IOException | CsvValidationException e) {
            LOGGER.error("Failed to parse basic athlete list file: {}", path, e);
        }

        return results;
    }

    private static String getFromArray(int index, String[] array) {
        if (array.length <= index) {
            return null;
        }
        String data = array[index];
        return data.isEmpty() ? null : data.trim();
    }

}
