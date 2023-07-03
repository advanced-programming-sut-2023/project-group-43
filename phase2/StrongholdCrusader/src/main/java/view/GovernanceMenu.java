package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GovernanceController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.net.URL;


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
    public ChoiceBox foodChoiceBox;
    public ChoiceBox taxChoiceBox;
    public ScrollBar fearScrollBar;
    public Label population;

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
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("-2", "-1", "0", "1", "2");
        foodChoiceBox.setItems(list);
        foodChoiceBox.setValue(governanceController.getGame().getCurrentPlayer().getGovernance().getFoodRate().getRateNumber());
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("-3", "-2", "-1", "0", "1", "2", "3");
        taxChoiceBox.setItems(list2);
        taxChoiceBox.setValue(governanceController.getGame().getCurrentPlayer().getGovernance().getTaxRate().getRateNumber());
        fearScrollBar.setMin(-3);
        fearScrollBar.setMax(8);
        fearScrollBar.setValue(governanceController.getGame().getCurrentPlayer().getGovernance().getFearRate());
        population.setText("population: " + governanceController.getGame().getCurrentPlayer().getGovernance().getPopulation());
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

    private String foodRate(int rate) {
        return (governanceController.foodRate(rate)).getString();
    }

    public String taxRate(int rate) {
        return (governanceController.taxRate(rate)).getString();
    }

    public String fearRate(int rate) {
        return (governanceController.fearRate(rate)).getString();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        GameController gameController = new GameController(governanceController.getGame());
        GameMenu gameMenu = new GameMenu();
        gameMenu.setGameController(gameController);
        gameMenu.start(RegisterMenu.getStage());
    }

    public void applyChanges(MouseEvent mouseEvent) {
        foodRate(Integer.parseInt(foodChoiceBox.getValue().toString()));
        taxRate(Integer.parseInt(taxChoiceBox.getValue().toString()));
        int fearRate = (int) Math.floor(fearScrollBar.getValue());
        fearRate(fearRate);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("changes saved successfully!");
        alert.show();
        updateImages();
    }
}
