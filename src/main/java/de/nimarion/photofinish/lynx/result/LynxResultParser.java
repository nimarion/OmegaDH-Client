package de.nimarion.photofinish.lynx.result;

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

public class LynxResultParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(LynxResultParser.class);
    private static final Charset CP1252 = Charset.forName("Cp1252");

    public static LynxResults parse(String path) {
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), CP1252))
                .withCSVParser(parser).build()) {
            String[] header = reader.readNext();
            if (header == null || Arrays.stream(header).allMatch(String::isBlank)) {
                LOGGER.warn("Lynx result file is empty: {}", path);
                return null;
            }

            int eventNumber = Integer.parseInt(getFromArray(0, header));
            int roundNumber = Integer.parseInt(getFromArray(1, header));
            int heatNumber = Integer.parseInt(getFromArray(2, header));
            String eventName = getFromArray(3, header);
            Float wind = parseFloatOrNull(getFromArray(4, header));
            String windUnit = getFromArray(5, header);
            String template = getFromArray(6, header);
            String captureTime = getFromArray(7, header);
            String caputureDuration = getFromArray(8, header);
            String distance = getFromArray(9, header);
            String startTime = getFromArray(10, header);
            String windMode = getFromArray(11, header);

            List<LynxResult> results = new ArrayList<>();
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (Arrays.stream(row).allMatch(String::isBlank)) {
                    break;
                }
                String placeStr = getFromArray(0, row);
                if (placeStr == null || !placeStr.chars().allMatch(Character::isDigit)) {
                    continue;
                }
                int place = Integer.parseInt(placeStr);
                int bib = Integer.parseInt(getFromArray(1, row));
                int lane = Integer.parseInt(getFromArray(2, row));
                String lastname = getFromArray(3, row);
                String firstname = getFromArray(4, row);
                String affiliation = getFromArray(5, row);
                String time = getFromArray(6, row);
                String license = getFromArray(7, row);
                //String deltaTime = getFromArray(8, row);
                String reacTime = getFromArray(9, row);
                /*String splits = getFromArray(10, row);
                String user1 = getFromArray(11, row);
                String user2 = getFromArray(12, row);
                String user3 = getFromArray(13, row);
                String deltaTime2 = getFromArray(14, row);
                String deltaTime3 = getFromArray(15, row);
                String speed = getFromArray(16, row);
                String pace = getFromArray(17, row);*/

                LynxResult result = new LynxResult(place, bib, lane, firstname, lastname, affiliation, time, license,
                        reacTime);
                results.add(result);
            }

            LynxEvent lynxEvent = new LynxEvent(eventNumber, roundNumber, heatNumber, eventName, wind, windUnit,
                    template, captureTime, caputureDuration, distance, startTime, windMode);
            return new LynxResults(lynxEvent, results);
        } catch (IOException | CsvValidationException e) {
            LOGGER.error("Failed to read Lynx result file: {}", path, e);
            return null;
        }
    }

    private static Float parseFloatOrNull(String value) {
        if (value == null) {
            return null;
        }
        try {
            return Float.parseFloat(value);
        } catch (NumberFormatException e) {
            LOGGER.debug("Non-numeric wind value '{}'", value, e);
            return null;
        }
    }

    private static String getFromArray(int index, String[] array) {
        if (array.length <= index) {
            return null;
        }
        String data = array[index];
        return data.isEmpty() ? null : data.trim();
    }

}
