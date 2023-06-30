package view;

import controller.GameControllers.ChangeEnvironmentController;
import controller.GameControllers.GameController;
import controller.MainUserController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.EnvironmentChangeCommands;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataBase;

import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ChangeEnvironmentMenu extends Application {
    public ChoiceBox rows;
    public ChoiceBox map;
    public ChoiceBox turns;
    public TextArea players;
    private Stage stage;
    private static ChangeEnvironmentController changeEnvironmentController;
    private String x, y, type;

    public ChangeEnvironmentController getChangeEnvironmentController() {
        return changeEnvironmentController;
    }

    public void setChangeEnvironmentController(ChangeEnvironmentController changeEnvironmentController) {
        ChangeEnvironmentMenu.changeEnvironmentController = changeEnvironmentController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        BorderPane changeEnvironmentMenuPane = FXMLLoader.load(new URL(Objects.requireNonNull(RegisterMenu.class.getResource("/fxml/changeEnvironmentMenu.fxml")).toExternalForm()));
        Scene scene = new Scene(changeEnvironmentMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("100", "150", "200", "250", "300");
        rows.setItems(list);
        rows.setValue("100");
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("1", "2");
        map.setItems(list2);
        map.setValue("1");
        ObservableList<String> list3 = FXCollections.observableArrayList();
        list3.addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        turns.setItems(list3);
        turns.setValue("1");
    }

    private Output setTexture(Matcher matcher) {
        if (parseMatcher(matcher)) {
            System.out.println(type);
            System.out.println(x);
            System.out.println(y);
            return changeEnvironmentController.setTexture(Integer.parseInt(x), Integer.parseInt(y), type);
        }

        return null;
    }

    private Output setTextureRectangle(Matcher matcher) {
        String x1 = Validations.getInfo("x1", matcher.group());
        String y1 = Validations.getInfo("y1", matcher.group());
        String x2 = Validations.getInfo("x2", matcher.group());
        String y2 = Validations.getInfo("y2", matcher.group());
        String type = Validations.getInfo("t", matcher.group());
        if (x1 == null || y1 == null || x2 == null || y2 == null || type == null) return null;
        if (!(x1.matches("\\d+") && y1.matches("\\d+") && x2.matches("\\d+") && y2.matches("\\d+")))
            return null;
        return changeEnvironmentController.setTextureRectangle(Integer.parseInt(x1), Integer.parseInt(y1),
                Integer.parseInt(x2), Integer.parseInt(y2), type);
    }

    private Output clear(Matcher matcher) {
        String x = Validations.getInfo("x", matcher.group());
        String y = Validations.getInfo("y", matcher.group());
        if (x == null || y == null || !x.matches("\\d+") || !y.matches("\\d+"))
            return null;
        return changeEnvironmentController.clear(Integer.parseInt(x), Integer.parseInt(y));
    }

    private Output dropRock(Matcher matcher) {
        String x = Validations.getInfo("x", matcher.group());
        String y = Validations.getInfo("y", matcher.group());
        String direction = Validations.getInfo("d", matcher.group());
        if (x == null || y == null || direction == null || !x.matches("\\d+") || !y.matches("\\d+"))
            return null;
        return changeEnvironmentController.dropRock(Integer.parseInt(x), Integer.parseInt(y), direction);
    }

    private Output dropTree(Matcher matcher) {
        if (parseMatcher(matcher))
            return changeEnvironmentController.dropTree(Integer.parseInt(x), Integer.parseInt(y), type);
        return null;
    }

    private Output dropBuilding(Matcher matcher) {
        if (parseMatcher(matcher))
            return changeEnvironmentController.dropBuilding(Integer.parseInt(x), Integer.parseInt(y), type);
        return null;
    }

    private boolean enterGameMenu() throws Exception {
        String allPlayers = players.getText();
        Scanner scanner = new Scanner(allPlayers);
        ArrayList<String> usernames = new ArrayList<>();
        //usernames.add(MainMenu.getUsername());
        while (scanner.hasNextLine()) {
            usernames.add(scanner.nextLine());
        }
        return changeEnvironmentController.enterGameMenu(usernames,
                Integer.parseInt(rows.getValue().toString()), Integer.parseInt(turns.getValue().toString()),
                Integer.parseInt(map.getValue().toString()));
    }

    private boolean parseMatcher(Matcher matcher) {
        x = null;
        y = null;
        type = null;
        x = Validations.getInfo("x", matcher.group());
        y = Validations.getInfo("y", matcher.group());
        type = Validations.getInfo("t", matcher.group());
        return x != null && y != null && type != null && x.matches("\\d+") && y.matches("\\d+");
    }

    public void startGame(MouseEvent mouseEvent) throws Exception {
        if (!enterGameMenu()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("you cannot start the game");
            alert.show();
        }

    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        //mainMenu.setMainUserController(MainMenu.getUsername());
        mainMenu.start(RegisterMenu.getStage());
    }
}
