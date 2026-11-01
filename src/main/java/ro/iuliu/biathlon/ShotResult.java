package ro.iuliu.biathlon;

public enum ShotResult {
    HIT,
    MISS;

    public static ShotResult fromSymbol(char symbol) {
        if (symbol == 'x') {
            return HIT;
        }

        if (symbol == 'o') {
            return MISS;
        }

        throw new IllegalArgumentException("Invalid shot symbol: " + symbol);
    }

    public int getPenaltySeconds() {
        if (this == MISS) {
            return 10;
        }

        return 0;
    }
}
