package ro.iuliu.biathlon;

public class Athlete {

    private int athleteNumber;
    private String name;
    private String countryCode;
    private int skiTimeSeconds;
    private String firstShooting;
    private String secondShooting;
    private String thirdShooting;

    public Athlete(int athleteNumber, String name, String countryCode,
                   int skiTimeSeconds, String firstShooting,
                   String secondShooting, String thirdShooting) {

        this.athleteNumber = athleteNumber;
        this.name = name;
        this.countryCode = countryCode;
        this.skiTimeSeconds = skiTimeSeconds;
        this.firstShooting = firstShooting;
        this.secondShooting = secondShooting;
        this.thirdShooting = thirdShooting;
    }

    public String getName() {
        return name;
    }

    public int getAthleteNumber() {
        return athleteNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public int getSkiTimeSeconds() {
        return skiTimeSeconds;
    }

    public String getFirstShooting() {
        return firstShooting;
    }

    public String getSecondShooting() {
        return secondShooting;
    }

    public String getThirdShooting() {
        return thirdShooting;
    }
}

