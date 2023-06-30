package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GovernanceController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.regex.Matcher;


public class GovernanceMenu extends Application {
    public Rectangle foodRec;
    public Rectangle taxRec;
    public Rectangle fearRec;
    public Rectangle religionRec;
    public Label foodRate;
    public Label taxRate;
    public Label fearRate;
    public Label religionRate;
    private static GovernanceController governanceController;
    public Rectangle main;
    public Rectangle back;
    public Rectangle allRec;
    public Label all;
    private Stage stage;

    private static Pane pane;

    public GovernanceController getGovernanceController() {
        return governanceController;
    }

    public void setGovernanceController(GovernanceController governanceController) {
        GovernanceMenu.governanceController = governanceController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = FXMLLoader.load(
                new URL((GovernanceMenu.class.getResource("/fxml/governanceMenu.fxml")).toExternalForm()));

        Scene scene = new Scene(pane);
        setBackground(pane);
        stage.setScene(scene);
        setBackground(pane);
        stage.show();
    }

    @FXML
    public void initialize() {
        main.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/face_mask/main.png").toExternalForm())));
        back.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/face_mask/back.png").toExternalForm())));
        updateImages();
    }

    private void updateImages() {
        int foodRateNum = governanceController.showFoodRate();
        int taxRateNum = governanceController.showTaxRate();
        int fearRateNum = governanceController.showFearRate();
        int religionRateNum = governanceController.showReligionRate();
        int allRateNum = -governanceController.getGame().getCurrentPlayer().getGovernance().getPopularityChange() +
                governanceController.getGame().getCurrentPlayer().getGovernance().getPopularity();
        setImage(foodRateNum, foodRec, foodRate);
        setImage(taxRateNum, taxRec, taxRate);
        setImage(fearRateNum, fearRec, fearRate);
        setImage(religionRateNum, religionRec, religionRate);
        setImage(allRateNum, allRec, all);
    }

    private void setImage(int rate, Rectangle rec, Label label) {
        int imageNumber = 2;
        label.setTextFill(Color.GRAY);
        if (rate < 0) {
            imageNumber = 1;
            label.setTextFill(Color.RED);
        } else if (rate > 0) {
            imageNumber = 3;
            label.setTextFill(Color.GREEN);
        }
        rec.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/face_mask/" + imageNumber + ".png").toExternalForm())));
        label.setText(((Integer) rate).toString());
    }

    private void setBackground(Pane pane) {
        pane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/oldPaper.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

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

    public void back(MouseEvent mouseEvent) throws Exception {
        GameController gameController = new GameController(governanceController.getGame());
        GameMenu gameMenu = new GameMenu();
        gameMenu.setGameController(gameController);
        gameMenu.start(RegisterMenu.getStage());
    }
}
