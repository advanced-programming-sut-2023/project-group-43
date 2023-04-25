package enums.Degrees;

public enum DefenseDegree {
    VERY_VERY_LOW(10),
    VERY_LOW(100),
    LOW(200),
    AVERAGE(300),
    HIGH(400),
    VERY_HIGH(500),
    ;

    private int degree;

    DefenseDegree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }
}
