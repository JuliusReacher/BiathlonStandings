package ro.iuliu.biathlon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class StandingsCalculatorTest {

    @Test
    void shouldRankAthletesByFinalTime() throws IOException {
        // Arrange
        String csv =
                "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n"
                        + "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n"
                        + "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

        CSVReader csvReader = new CSVReader();
        List<Athlete> athletes = csvReader.read(new StringReader(csv));
        StandingsCalculator standingsCalculator = new StandingsCalculator();

        // Act
        List<Athlete> standings = standingsCalculator.calculate(athletes);

        // Assert
        Assertions.assertEquals(3, standings.size());
        Assertions.assertEquals("Piotr Smitzer", standings.get(0).getName());
        Assertions.assertEquals("Jimmy Smiles", standings.get(1).getName());
        Assertions.assertEquals("Umar Jorgson", standings.get(2).getName());
    }
}