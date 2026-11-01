package ro.iuliu.biathlon;

public class BiathlonCalculator {

    public int calculatePenaltySeconds(Athlete athlete) {
        int firstPenalty = calculateShootingPenalty(athlete.getFirstShooting());
        int secondPenalty = calculateShootingPenalty(athlete.getSecondShooting());
        int thirdPenalty = calculateShootingPenalty(athlete.getThirdShooting());

        return firstPenalty + secondPenalty + thirdPenalty;
    }

    public int calculateFinalTimeSeconds(Athlete athlete) {
        return athlete.getSkiTimeSeconds() + calculatePenaltySeconds(athlete);
    }

    private int calculateShootingPenalty(String shooting) {
        if (shooting.length() != 5) {
            throw new IllegalArgumentException("A shooting range must have 5 shots");
        }

        int penalty = 0;

        for (int i = 0; i < shooting.length(); i++) {
            char symbol = shooting.charAt(i);
            ShotResult result = ShotResult.fromSymbol(symbol);
            penalty += result.getPenaltySeconds();
        }

        return penalty;
    }
}