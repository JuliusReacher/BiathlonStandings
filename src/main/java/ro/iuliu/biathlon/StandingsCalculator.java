package ro.iuliu.biathlon;

import java.util.List;

public class StandingsCalculator {

    public List<Athlete> calculate(List<Athlete> athletes) {
        athletes.sort(new AthleteTimeComparator());
        return athletes;
    }
}