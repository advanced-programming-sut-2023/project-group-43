package view;

import controller.GameControllers.GovernanceController;
import enums.Output;
import enums.menuEnums.GovernanceMenuCommands;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.regex.Matcher;


public class GovernanceMenu extends Application {
    private GovernanceController governanceController;
    private Stage stage;

    public GovernanceController getGovernanceController() {
        return governanceController;
    }

    public void setGovernanceController(GovernanceController governanceController) {
        this.governanceController = governanceController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
    }



    private String foodRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return (governanceController.foodRate(rate)).getString();
    }

    public String taxRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return (governanceController.taxRate(rate)).getString();
    }

    public String fearRate(Matcher matcher) {
        int rate = Integer.parseInt(matcher.group("rate"));
        return (governanceController.fearRate(rate)).getString();
    }

}
