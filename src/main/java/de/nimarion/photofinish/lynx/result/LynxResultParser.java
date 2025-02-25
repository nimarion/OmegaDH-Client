package de.nimarion.photofinish.lynx.result;

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

public class LynxResultParser {

    public static LynxResults parse(String path) {
        List<String[]> csvData = new ArrayList<>();
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

        String[] header = csvData.get(0);
        int eventNumber = Integer.parseInt(getFromArray(0, header));
        int roundNumber = Integer.parseInt(getFromArray(1, header));
        int heatNumber = Integer.parseInt(getFromArray(2, header));
        String eventName = getFromArray(3, header);
        Float wind = header[4].isEmpty() ? null : Float.parseFloat(header[4]);
        String windUnit = getFromArray(5, header);
        String template = getFromArray(6, header);
        String captureTime = getFromArray(7, header);
        String caputureDuration = getFromArray(8, header);
        String distance = getFromArray(9, header);
        String startTime = getFromArray(10, header);
        String windMode = getFromArray(11, header);

        List<LynxResult> results = new ArrayList<>();

        for (String[] arrays : csvData.subList(1, csvData.size())) {
            if (Arrays.stream(arrays).allMatch(String::isBlank)) {
                break;
            }
            if (arrays[0].isEmpty()) {
                continue;
            }
            if (!arrays[0].chars().allMatch(Character::isDigit)) {
                continue;
            }
            int place = Integer.parseInt(getFromArray(0, arrays));
            int bib = Integer.parseInt(getFromArray(1, arrays));
            int lane = Integer.parseInt(getFromArray(2, arrays));
            String lastname = getFromArray(3, arrays);
            String firstname = getFromArray(4, arrays);
            String affiliation = getFromArray(5, arrays);
            String time = getFromArray(6, arrays);
            String license = getFromArray(7, arrays);
            //String deltaTime = getFromArray(8, arrays);
            String reacTime = getFromArray(9, arrays);
            /*String splits = getFromArray(10, arrays);
            String user1 = getFromArray(11, arrays);
            String user2 = getFromArray(12, arrays);
            String user3 = getFromArray(13, arrays);
            String deltaTime2 = getFromArray(14, arrays);
            String deltaTime3 = getFromArray(15, arrays);
            String speed = getFromArray(16, arrays);
            String pace = getFromArray(17, arrays);*/

            LynxResult result = new LynxResult(place, bib, lane, firstname, lastname, affiliation, time, license,
                    reacTime);
            results.add(result);
        }

        LynxEvent lynxEvent =  new LynxEvent(eventNumber, roundNumber, heatNumber, eventName, wind, windUnit,
                template, captureTime, caputureDuration, distance, startTime, windMode);
        return new LynxResults(lynxEvent, results);
    }

    private static String getFromArray(int index, String[] array) {
        if (array.length <= index) {
            return null;
        }
        String data = array[index];
        return data.isEmpty() ? null : data.trim();
    }

}
