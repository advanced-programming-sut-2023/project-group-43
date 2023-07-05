package view;

import controller.GameControllers.ChangeEnvironmentController;
import controller.RegisterAndLoginController;
import enums.Output;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DataBase;
import model.User;

import java.net.URL;
import java.util.Objects;

public class MapTransfer extends Application {
    private static Stage stage;
    public TextField mapName;
    public TextField reciever;

    private Scene scene;
    private Pane pane;
    private User user;
    private String name;
    private Pane selectedPane;

    public MapTransfer(User user, String name, Pane selectedPane) {
        this.user = user;
        this.name = name;
        this.selectedPane = selectedPane;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        pane = FXMLLoader.load(
                new URL(Objects.requireNonNull(RegisterMenu.class.getResource("/fxml/mapTransfer.fxml")).toExternalForm()));
        scene = new Scene(pane);
        setBackground();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }
    /*@FXML
    public void initialize() {

    }*/
    private void setBackground() {
        pane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/mapStore.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    public void save(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (DataBase.getInstance().getUserByUsername(MainMenu.getUsername()).equals(user)) {
            alert.setContentText("you already have a map with this name!\nselect another map");
        } else if (DataBase.getInstance().getUserByUsername(MainMenu.getUsername()).mapWithThisName(mapName.getText().toString())) {
            alert.setContentText("you already have a map with this name!\nenter another name");
        } else {
            DataBase.getInstance().getUserByUsername(MainMenu.getUsername()).AddToMapsOfThisUser(mapName.getText().toString(), selectedPane);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText("you already have a map with this name!\nenter another name");
        }
        alert.show();
        try {
            (new ChangeEnvironmentMenu()).start(RegisterMenu.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void send(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()));
        changeEnvironmentMenu.setChangeEnvironmentController(changeEnvironmentController);
        try {
            changeEnvironmentMenu.start(RegisterMenu.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
