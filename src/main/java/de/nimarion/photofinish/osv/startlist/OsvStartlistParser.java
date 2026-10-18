package de.nimarion.photofinish.osv.startlist;

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

public class OsvStartlistParser extends StartlistParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(OsvStartlistParser.class);
    private static final Charset CP1252 = Charset.forName("Cp1252");

    public OsvStartlistParser(String path) {
        super(path);
    }

    @Override
    protected List<Startlist> parse(String path) {
        List<Startlist> startlists = new ArrayList<>();
        CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(path), CP1252))
                .withCSVParser(parser).withSkipLines(1).build()) {
            OsvStartlist currentStartlist = null;
            String[] row;
            while ((row = reader.readNext()) != null) {
                if (Arrays.stream(row).allMatch(String::isBlank)) {
                    continue;
                }
                if (!row[0].isBlank()) {
                    String eventCode = row[0].trim();
                    String date = row[1].trim();
                    String startTime = row[2].trim();
                    int length = Integer.parseInt(row[8].trim());
                    String title = row[9].trim();
                    currentStartlist = new OsvStartlist(eventCode, length, title, date, startTime);
                    startlists.add(currentStartlist);
                    continue;
                }
                if (currentStartlist == null) {
                    continue;
                }
                int lane = Integer.parseInt(row[3].trim());
                int bib = Integer.parseInt(row[4].trim());
                String lastname = row[5].trim();
                String firstname = row[6].trim();
                String nation = row[7].trim();
                Competitor member = new Competitor(firstname, lastname, lane, bib, nation);
                currentStartlist.addCompetitor(member);
            }
        } catch (IOException | CsvValidationException e) {
            LOGGER.error("Failed to parse OSV startlist file: {}", path, e);
        }
        return startlists;
    }

}
