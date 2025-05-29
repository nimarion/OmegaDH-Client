package de.nimarion.photofinish.taf;


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

public class BasicAthleteListParser {

    public static List<BasicAthlete> parse(String path) {
        List<String[]> csvData = new ArrayList<>();
        String fileName = path;

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(fileName), "Cp1252"))
                .withCSVParser(parser).build()) {
            csvData = reader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

        List<BasicAthlete> results = new ArrayList<>();

        for (String[] arrays : csvData.subList(1, csvData.size())) {
            if (Arrays.stream(arrays).allMatch(String::isBlank)) {
                break;
            }            

            Integer bib = Integer.parseInt(getFromArray(1, arrays));
            String firstname = getFromArray(3, arrays);
            String lastname = getFromArray(4, arrays);
            String country = getFromArray(11, arrays);	
            System.out.println("Bib: " + bib + ", Firstname: " + firstname + ", Lastname: " + lastname + ", Country: " + country);

            BasicAthlete basicAthlete = new BasicAthlete(bib, firstname, lastname, country);
            results.add(basicAthlete);
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
