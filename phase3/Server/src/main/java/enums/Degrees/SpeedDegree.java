package enums.Degrees;

public enum SpeedDegree {
    VERY_LOW(1),
    LOW(2),
    AVERAGE(3),
    HIGH(4),
    VERY_HIGH(5),
    ;
    private double degree;

    SpeedDegree(double degree) {
        this.degree = degree;
    }

    public double getDegree() {
        return degree;
    }
}
