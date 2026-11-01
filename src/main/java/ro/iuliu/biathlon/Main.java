package ro.iuliu.biathlon;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/biathlon.csv";

        if (args.length > 0) {
            filePath = args[0];
        }

        CSVReader csvReader = new CSVReader();
        List<Athlete> athletes = csvReader.read(
                new FileReader(filePath, StandardCharsets.UTF_8)
        );

        StandingsCalculator standingsCalculator = new StandingsCalculator();
        List<Athlete> standings = standingsCalculator.calculate(athletes);

        BiathlonCalculator calculator = new BiathlonCalculator();
        String[] places = {"Winner", "Runner-up", "Third Place"};

        for (int i = 0; i < standings.size() && i < 3; i++) {
            Athlete athlete = standings.get(i);
            int finalTime = calculator.calculateFinalTimeSeconds(athlete);
            int penalty = calculator.calculatePenaltySeconds(athlete);

            System.out.println(
                    places[i] + " - " + athlete.getName() + " "
                            + formatTime(finalTime)
                            + " (" + formatTime(athlete.getSkiTimeSeconds())
                            + " + " + penalty + ")"
            );
        }
    }

    private static String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;

        return String.format("%d:%02d", minutes, seconds);
    }
}