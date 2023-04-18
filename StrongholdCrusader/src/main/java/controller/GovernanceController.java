package controller;

import enums.Output;
import model.DataBase;

public class GovernanceController {

    private DataBase dataBase;

    public GovernanceController(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    public static Output showPopularityFactors() { return null;}

    public static Output showPopularity() {return null;}

    public static Output showFoodList() {return null;}

    public static Output foodRate(int rate) {return null;}

    public static Output showFoodRate() {return null;}

    public static Output taxRate(int rate) {return null;}

    public static Output showTaxRate() {return null;}

    public static Output fearRate(int rate) {return null;}

    public static Output showFearRate() {return null;}

}
