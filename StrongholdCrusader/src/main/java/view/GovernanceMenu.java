package view;

import controller.GameControllers.GovernanceController;
import enums.Output;
import enums.menuEnums.GovernanceMenuCommands;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.DataBase;
import model.User;

import java.net.URL;
import java.util.Objects;
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
    private GovernanceController governanceController;
    private Stage stage;

    private static Pane pane;

    public GovernanceController getGovernanceController() {
        return governanceController;
    }

    public void setGovernanceController(GovernanceController governanceController) {
        this.governanceController = governanceController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = FXMLLoader.load(
                new URL(Objects.requireNonNull(LoginMenu.class.getResource("/fxml/loginMenu.fxml")).toExternalForm()));

        Scene scene = new Scene(pane);
        setBackground(pane);
        stage.setScene(scene);
        setBackground(pane);
        stage.show();
    }

    @FXML
    public void initialize() {
        updateImages();
    }

    private void updateImages() {
        int foodRateNum = governanceController.showFoodRate();
        int taxRateNum = governanceController.showTaxRate();
        int fearRateNum = governanceController.showFearRate();
        int religionRateNum = getGovernanceController().showReligionRate();
        setImage(foodRateNum, foodRec, foodRate);
        setImage(taxRateNum, taxRec, taxRate);
        setImage(fearRateNum, fearRec, fearRate);
        setImage(religionRateNum, religionRec, religionRate);
    }

    private void setImage(int rate, Rectangle rec, Label label) {
        int imageNumber = 2;
        if (rate < 0) imageNumber = 1;
        else if (rate > 0) imageNumber = 3;
        rec.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/face_mask/" + imageNumber + ".png").toExternalForm())));
        label.setText(((Integer)rate).toString());
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

}
