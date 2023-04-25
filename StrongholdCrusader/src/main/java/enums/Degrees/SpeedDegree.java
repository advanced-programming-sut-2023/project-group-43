package enums.Degrees;

public enum SpeedDegree {
    VERY_LOW(100),
    LOW(200),
    AVERAGE(300),
    HIGH(400),
    VERY_HIGH(500),
    ;
    private int degree;

    SpeedDegree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }
}
