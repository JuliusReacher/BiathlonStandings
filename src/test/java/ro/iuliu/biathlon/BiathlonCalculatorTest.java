package ro.iuliu.biathlon;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BiathlonCalculatorTest {

    private BiathlonCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new BiathlonCalculator();
    }

    @Test
    void shouldCalculatePiotrResultWithoutPenalty() {
        // Arrange
        Athlete athlete = new Athlete(
                27, "Piotr Smitzer", "CZ", 1810,
                "xxxxx", "xxxxx", "xxxxx"
        );

        // Act
        int actual = calculator.calculateFinalTimeSeconds(athlete);

        // Assert
        Assertions.assertEquals(0, calculator.calculatePenaltySeconds(athlete));
        Assertions.assertEquals(1810, actual);
    }

    @Test
    void shouldAddSixtySecondsForJimmy() {
        // Arrange
        Athlete athlete = new Athlete(
                1, "Jimmy Smiles", "UK", 1755,
                "xxoox", "xooxo", "xxxxo"
        );

        // Act
        int actual = calculator.calculateFinalTimeSeconds(athlete);

        // Assert
        Assertions.assertEquals(60, calculator.calculatePenaltySeconds(athlete));
        Assertions.assertEquals(1815, actual);
    }

    @Test
    void shouldAddThirtySecondsForUmar() {
        // Arrange
        Athlete athlete = new Athlete(
                11, "Umar Jorgson", "SK", 1827,
                "xxxox", "xxxxx", "xxoxo"
        );

        // Act
        int actual = calculator.calculateFinalTimeSeconds(athlete);

        // Assert
        Assertions.assertEquals(30, calculator.calculatePenaltySeconds(athlete));
        Assertions.assertEquals(1857, actual);
    }
}