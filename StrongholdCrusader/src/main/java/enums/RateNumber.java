package enums;

public enum RateNumber {
    //foods
    FOOD_RATE_MINUS_2( "food",-2,0,0,-8),
    FOOD_RATE_MINUS_1("food", -1,0,0,-4),
    FOOD_RATE_0("food", 0,0,1,0),
    FOOD_RATE_1("food", 1,0,1.5,4),
    FOOD_RATE_2("food", 2,0,2,8),
    //taxes
    TAX_RATE_MINUS_3("tax", -3,0,1,7),
    TAX_RATE_MINUS_2("tax", -2,0 , 0.8 , 5),
    TAX_RATE_MINUS_1("tax", -1,0,0.6 ,3),
    TAX_RATE_0("tax", 0,0,0,1),
    TAX_RATE_POSITIVE_1("tax", 1,0.6,0,-2),
    TAX_RATE_POSITIVE_2("tax", 2,0.8,0,-4),
    TAX_RATE_POSITIVE_3("tax", 3,1,0,-6),
    TAX_RATE_POSITIVE_4("tax", 4,1.2,0,-8),
    TAX_RATE_POSITIVE_5("tax", 5,1.4,0,-12),
    TAX_RATE_POSITIVE_6("tax", 6,1.6,0,-16),
    TAX_RATE_POSITIVE_7("tax", 7,1.8,0,-20),
    TAX_RATE_POSITIVE_8("tax", 8,2,0,-24)
    ;
    private String type;
    private int rateNumber;
    private double tax;
    private double payment;
    private int popularityIncrement;

    RateNumber(String type, int rateNumber, double tax, double payment, int popularityIncrement) {
        this.type = type;
        this.rateNumber = rateNumber;
        this.tax = tax;
        this.payment = payment;
        this.popularityIncrement = popularityIncrement;
    }
    public static RateNumber getRateNumberEnumByTypeAndRateNumber(String type, int rateNumber){
            for(RateNumber value : RateNumber.values()) {
                if (value.getType().equals(type) && value.getRateNumber() == rateNumber)
                    return value;
            }
            return null;
    }

    public String getType() {
        return type;
    }

    public void setRateNumber(int rateNumber) {
        this.rateNumber = rateNumber;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void setPopularityIncrement(int popularityIncrement) {
        this.popularityIncrement = popularityIncrement;
    }

    public int getRateNumber() {
        return rateNumber;
    }

    public double getTax() {
        return tax;
    }

    public double getPayment() {
        return payment;
    }

    public int getPopularityIncrement() {
        return popularityIncrement;
    }
}
