package enums;

public enum RateNumber {
    //foods
    FOOD_RATE_MINUS_2(-2,0,0,-8),
    FOOD_RATE_MINUS_1(-1,0,0,-4),
    FOOD_RATE_0(0,0,1,0),
    FOOD_RATE_1(1,0,1.5,4),
    FOOD_RATE_2(2,0,2,8),
    //taxes
    TAX_RATE_MINUS_3(-3,0,1,7),
    TAX_RATE_MINUS_2(-2,0 , 0.8 , 5),
    TAX_RATE_MINUS_1(-1,0,0.6 ,3),
    TAX_RATE_0(0,0,0,1),
    TAX_RATE_POSITIVE_1(1,0.6,0,-2),
    TAX_RATE_POSITIVE_2(2,0.8,0,-4),
    TAX_RATE_POSITIVE_3(3,1,0,-6),
    TAX_RATE_POSITIVE_4(4,1.2,0,-8),
    TAX_RATE_POSITIVE_5(5,1.4,0,-12),
    TAX_RATE_POSITIVE_6(6,1.6,0,-16),
    TAX_RATE_POSITIVE_7(7,1.8,0,-20),
    TAX_RATE_POSITIVE_8(8,2,0,-24)
    ;
    private int rateNumber;
    private double tax;
    private double payment;
    private int popularityIncrement;

    RateNumber(int rateNumber, double tax, double payment, int popularityIncrement) {
        this.rateNumber = rateNumber;
        this.tax = tax;
        this.payment = payment;
        this.popularityIncrement = popularityIncrement;
    }

    public int getRateNumber() {
        return rateNumber;
    }
}
