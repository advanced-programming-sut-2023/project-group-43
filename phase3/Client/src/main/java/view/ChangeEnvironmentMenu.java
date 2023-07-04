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
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
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
    private BorderPane changeEnvironmentMenuPane ;
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
        changeEnvironmentMenuPane = FXMLLoader.load(new URL(Objects.requireNonNull(RegisterMenu.class.getResource("/fxml/changeEnvironmentMenu.fxml")).toExternalForm()));
        setBackground();
        Scene scene = new Scene(changeEnvironmentMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("10", "20", "30", "40");
        rows.setItems(list);
        rows.setValue("20");
        ObservableList<String> list2 = FXCollections.observableArrayList();
        list2.addAll("1", "2");
        map.setItems(list2);
        map.setValue("1");
        ObservableList<String> list3 = FXCollections.observableArrayList();
        list3.addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        turns.setItems(list3);
        turns.setValue("1");
    }


    private void setBackground() {
        changeEnvironmentMenuPane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/5559468.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }


    private boolean enterGameMenu() throws Exception {
        String allPlayers = players.getText();
        Scanner scanner = new Scanner(allPlayers);
        ArrayList<String> usernames = new ArrayList<>();
        //this is for current user
        usernames.add(MainMenu.getUsername());
        while (scanner.hasNextLine()) {
            String username = scanner.nextLine();
            System.out.println(username);
            usernames.add(username);
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
        mainMenu.setMainUserController(MainMenu.getUsername());
        mainMenu.start(RegisterMenu.getStage());
    }

    public void mapStore(MouseEvent mouseEvent) {
        MapStore mapStore = new MapStore();
        mapStore.setCurrentUser(changeEnvironmentController.getCurrentUser());
        //mapStore.start(RegisterMenu.getStage());
    }
}
