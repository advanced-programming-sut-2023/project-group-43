package view;

import controller.GameControllers.ChangeEnvironmentController;
import controller.MainUserController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Cell;
import model.DataBase;
import model.User;

import java.net.URL;
import java.util.Objects;

public class MapStore extends Application {
    public User currentUser;
    public Stage stage;
    public Scene scene;
    public Pane pane = new Pane();
    public TextField choosePlayer;
    public Label choosePlayerError;
    public User user;
    public TextField chooseMapNumber;
    public Label chooseMapNumberError;
    public TextField third;
    public Label thirdError;
    public TextField mapName;
    public User secondUser;
    public Cell[][] selectedMap;
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        pane = FXMLLoader.load(new URL(ProfileMenu.class.getResource("/fxml/mapStore.fxml").toExternalForm()));
        scene = new Scene(pane);
        pane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/mapStore.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void initialize() {
        choosePlayer.textProperty().addListener((observable, oldText, newText) -> {
            if ((user = DataBase.getInstance().getUserByUsername(choosePlayer.getText())) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("this user has \"").append(user.getMapsOfThisUser().size()).append("\" maps");
                choosePlayerError.setText(stringBuilder.toString());
        }
            else choosePlayerError.setText("error : no user found with this name");
        });
        chooseMapNumber.textProperty().addListener((observable, oldText, newText) -> {
            if (user != null && user.getMapsOfThisUser().size() >= 1 &&
                    1 <= Integer.parseInt(chooseMapNumber.getText()) && user.getMapsOfThisUser().size() >= Integer.parseInt(chooseMapNumber.getText())) {
                selectedMap = user.getMapsOfThisUser().get(Integer.parseInt(chooseMapNumber.getText()) - 1);
                chooseMapNumberError.setText("ok");
            }
            chooseMapNumberError.setText("error : invalid map number");
        });
        third.textProperty().addListener((observable, oldText, newText) -> {
            if ((secondUser = DataBase.getInstance().getUserByUsername(choosePlayer.getText())) != null) {
                thirdError.setText("ok");
            }
            chooseMapNumberError.setText("error : user not found");
        });
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void sendMap(MouseEvent mouseEvent) {
        if (chooseMapNumberError.getText().equals("ok") && thirdError.getText().equals("ok") &&
                user.equals(currentUser) && !user.equals(secondUser)) {
            if (!secondUser.mapWithThisName(currentUser.getNameByMap(selectedMap))) {
                secondUser.addToMapsOfThisUser(currentUser.getNameByMap(selectedMap), selectedMap);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("map sent successfully");
                alert.show();
            }
        }
    }

    public void saveMap(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if (mapName.getText()!= null && !currentUser.mapWithThisName(mapName.getText()) && chooseMapNumberError.getText().equals("ok") &&
                currentUser != user && !choosePlayerError.getText().equals("error : no user found with this name")) {
            currentUser.addToMapsOfThisUser(mapName.getText(), selectedMap);
            alert.setContentText("map saved successfully");
        } else if (!(mapName.getText()!= null && !currentUser.mapWithThisName(mapName.getText()))) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("enter another map name!");
        } else if (!chooseMapNumberError.getText().equals("ok")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("enter map number correctly!");
        } else if (currentUser.equals(user)) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("you already have this map saved!");
        } else if (choosePlayerError.getText().equals("error : no user found with this name")) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText("enter the first field correctly!\nno user with this name found!");
        }
        alert.show();
    }

    public void back(MouseEvent mouseEvent) {
        ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController(currentUser);
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        changeEnvironmentMenu.setChangeEnvironmentController(changeEnvironmentController);
        try {
            changeEnvironmentMenu.start(RegisterMenu.getStage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
