package ro.iuliu.biathlon;

import java.util.Comparator;

public class AthleteTimeComparator implements Comparator<Athlete> {

    private BiathlonCalculator calculator = new BiathlonCalculator();

    @Override
    public int compare(Athlete first, Athlete second) {
        int firstTime = calculator.calculateFinalTimeSeconds(first);
        int secondTime = calculator.calculateFinalTimeSeconds(second);

        return Integer.compare(firstTime, secondTime);
    }
}
