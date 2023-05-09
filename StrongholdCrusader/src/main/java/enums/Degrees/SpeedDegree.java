package enums.Degrees;

public enum SpeedDegree {
    VERY_LOW(100),
    LOW(200),
    AVERAGE(300),
    HIGH(400),
    VERY_HIGH(500),
    ;
    private double degree;

    SpeedDegree(double degree) {
        this.degree = degree;
    }

    public double getDegree() {
        return degree;
    }
}
