package ro.iuliu.biathlon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public List<Athlete> read(Reader source) throws IOException {
        List<Athlete> athletes = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(source)) {
            String line = reader.readLine();

            while (line != null) {
                if (!line.trim().isEmpty()) {
                    athletes.add(parseLine(line));
                }

                line = reader.readLine();
            }
        }

        return athletes;
    }

    private Athlete parseLine(String line) {
        String[] columns = line.split(",", -1);

        if (columns.length != 7) {
            throw new IllegalArgumentException("A CSV row must have 7 columns");
        }

        for (int i = 0; i < columns.length; i++) {
            columns[i] = columns[i].trim();
        }

        int athleteNumber = Integer.parseInt(columns[0]);
        String name = columns[1];
        String countryCode = columns[2];

        String[] timeParts = columns[3].split(":", -1);

        if (timeParts.length != 2) {
            throw new IllegalArgumentException("Time must have minutes:seconds");
        }

        int minutes = Integer.parseInt(timeParts[0]);
        int seconds = Integer.parseInt(timeParts[1]);

        if (minutes < 0 || seconds < 0 || seconds > 59) {
            throw new IllegalArgumentException("Invalid skiing time");
        }

        int skiTimeSeconds = minutes * 60 + seconds;

        validateShooting(columns[4]);
        validateShooting(columns[5]);
        validateShooting(columns[6]);

        return new Athlete(
                athleteNumber, name, countryCode, skiTimeSeconds,
                columns[4], columns[5], columns[6]
        );
    }

    private void validateShooting(String shooting) {
        if (shooting.length() != 5) {
            throw new IllegalArgumentException("A shooting range must have 5 shots");
        }

        for (int i = 0; i < shooting.length(); i++) {
            ShotResult.fromSymbol(shooting.charAt(i));
        }
    }
}