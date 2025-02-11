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

import de.nimarion.photofinish.common.startlist.Competitor;
import de.nimarion.photofinish.common.startlist.Startlist;
import de.nimarion.photofinish.common.startlist.StartlistParser;

public class OsvStartlistParser extends StartlistParser{

    private List<String[]> csvData;

    public OsvStartlistParser(String path) {
        super(path);
    }

    private OsvStartlist forEventCode(String eCode) {
        OsvStartlist startlistEvent = null;
        for (String[] arrays : csvData) {
            String eventCode = arrays[0];
            String date = arrays[1];
            String startTime = arrays[2];

            String length = arrays[8];
            String title = arrays[9];
            if (arrays[0].equals(eCode)) {
                startlistEvent = new OsvStartlist(eventCode, Integer.parseInt(length), title, date, startTime);
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

    private String[] getAllEventCodes() {
        return csvData.stream()
                .map(arrays -> arrays[0])
                .filter(code -> !code.isEmpty())
                .toArray(String[]::new);
    }

    @Override
    protected List<Startlist> parse(String path) {
        List<Startlist> startlists = new ArrayList<>();
        csvData = new ArrayList<>();

        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), "Cp1252"))
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
        return startlists;
    }

}
