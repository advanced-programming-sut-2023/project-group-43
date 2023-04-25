package enums.Degrees;

public enum DamageDegree {
    VERY_LOW(100),
    LOW(200),
    AVERAGE(300),
    HIGH(400),
    VERY_HIGH(500)
    ;
    private int degree;

    DamageDegree(int degree) {
        this.degree = degree;
    }

    public int getDegree() {
        return degree;
    }
}
