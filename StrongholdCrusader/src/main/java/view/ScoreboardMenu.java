package view;

import controller.UserControllers.ScoreboardController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ScoreboardMenu extends Application {
    private static ScoreboardController scoreboardController;
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static ScoreboardController getScoreboardController() {
        return scoreboardController;
    }

    public static void setScoreboardController(ScoreboardController scoreboardController) {
        ScoreboardMenu.scoreboardController = scoreboardController;
    }
}
