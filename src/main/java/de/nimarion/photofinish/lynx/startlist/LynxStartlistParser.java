package de.nimarion.photofinish.lynx.startlist;

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

import de.nimarion.photofinish.common.startlist.Competitor;
import de.nimarion.photofinish.common.startlist.Startlist;
import de.nimarion.photofinish.common.startlist.StartlistParser;
import de.nimarion.photofinish.lynx.scoreboard.FinishLynxClient;

public class LynxStartlistParser extends StartlistParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(LynxStartlistParser.class);
    private static final Charset CP1252 = Charset.forName("Cp1252");

    public LynxStartlistParser(String path) {
        super(path);
    }

    public Startlist getStartlist(int eventNumber, int roundNumber, int heatNumber) {
        return getStartlists().stream()
                .filter(s -> FinishLynxClient.getEventNumberFromId(s.getId()) == eventNumber &&
                        FinishLynxClient.getRoundNumberFromId(s.getId()) == roundNumber &&
                        FinishLynxClient.getHeatNumberFromId(s.getId()) == heatNumber)
                .findFirst()
                .orElse(null);
    }

    @Override
    protected List<Startlist> parse(String path) {
        List<Startlist> startlists = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(',').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), CP1252))
                .withCSVParser(parser).build()) {
            Startlist currentStartlist = null;
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (Arrays.stream(row).allMatch(String::isBlank)) {
                    currentStartlist = null;
                    continue;
                }
                if (!row[0].isBlank()) {
                    int eventNumber = Integer.parseInt(row[0].trim());
                    int roundNumber = Integer.parseInt(row[1].trim());
                    int heatNumber = Integer.parseInt(row[2].trim());
                    String eventName = row.length > 3 ? row[3].trim() : "";
                    int distance = (row.length > 9 && !row[9].isBlank()) ? Integer.parseInt(row[9].trim()) : 0;
                    currentStartlist = new Startlist(eventNumber + "-" + roundNumber + "-" + heatNumber, distance,
                            eventName);
                    startlists.add(currentStartlist);
                    continue;
                }
                if (currentStartlist == null) {
                    continue;
                }
                int bib = Integer.parseInt(row[1].trim());
                int lane = Integer.parseInt(row[2].trim());
                String lastname = row.length > 3 ? row[3].trim() : "";
                String firstname = row.length > 4 ? row[4].trim() : "";
                String nation = row.length > 5 ? row[5].trim() : "";
                Competitor member = new Competitor(firstname, lastname, lane, bib, nation);
                currentStartlist.addCompetitor(member);
            }
        } catch (IOException | CsvValidationException e) {
            LOGGER.error("Failed to parse Lynx startlist file: {}", path, e);
        }
        return startlists;
    }

}
