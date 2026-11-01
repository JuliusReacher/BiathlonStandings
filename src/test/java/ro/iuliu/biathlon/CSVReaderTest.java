package ro.iuliu.biathlon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

public class CSVReaderTest {

    private CSVReader csvReader;

    @BeforeEach
    void setUp() {
        csvReader = new CSVReader();
    }

    @Test
    void shouldReadAllAthletesWithoutHeader() throws IOException {
        // Arrange
        String csv =
                "11,Umar Jorgson,SK,30:27,xxxox,xxxxx,xxoxo\n"
                        + "1,Jimmy Smiles,UK,29:15,xxoox,xooxo,xxxxo\n"
                        + "27,Piotr Smitzer,CZ,30:10,xxxxx,xxxxx,xxxxx";

        // Act
        List<Athlete> athletes = csvReader.read(new StringReader(csv));

        // Assert
        Assertions.assertEquals(3, athletes.size());

        Athlete first = athletes.get(0);

        Assertions.assertEquals(11, first.getAthleteNumber());
        Assertions.assertEquals("Umar Jorgson", first.getName());
        Assertions.assertEquals("SK", first.getCountryCode());
        Assertions.assertEquals(1827, first.getSkiTimeSeconds());
        Assertions.assertEquals("xxxox", first.getFirstShooting());
        Assertions.assertEquals("xxxxx", first.getSecondShooting());
        Assertions.assertEquals("xxoxo", first.getThirdShooting());

        Assertions.assertEquals("Jimmy Smiles", athletes.get(1).getName());
        Assertions.assertEquals(1755, athletes.get(1).getSkiTimeSeconds());

        Assertions.assertEquals("Piotr Smitzer", athletes.get(2).getName());
        Assertions.assertEquals(1810, athletes.get(2).getSkiTimeSeconds());
    }

    @Test
    void shouldRejectWrongNumberOfColumns() {
        // Arrange
        String csv = "11,Umar Jorgson,SK,30:27,xxxox,xxxxx";

        // Act & Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> csvReader.read(new StringReader(csv))
        );
    }

    @Test
    void shouldRejectInvalidTime() {
        // Arrange
        String csv = "11,Umar Jorgson,SK,30:75,xxxox,xxxxx,xxoxo";

        // Act & Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> csvReader.read(new StringReader(csv))
        );
    }

    @Test
    void shouldRejectWrongNumberOfShots() {
        // Arrange
        String csv = "11,Umar Jorgson,SK,30:27,xxxx,xxxxx,xxoxo";

        // Act & Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> csvReader.read(new StringReader(csv))
        );
    }

    @Test
    void shouldRejectInvalidShotSymbol() {
        // Arrange
        String csv = "11,Umar Jorgson,SK,30:27,xxxzx,xxxxx,xxoxo";

        // Act & Assert
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> csvReader.read(new StringReader(csv))
        );
    }
}
