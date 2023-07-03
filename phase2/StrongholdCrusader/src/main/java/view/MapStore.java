package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.User;

import java.nio.Buffer;

public class MapStore {
    public User currentUser;
    public Stage stage;
    public Scene scene;
    public Pane pane;
    public TextField choosePlayer;
    public TextField chooseMapNumber;
    public TextField third;
    public Button sendButton;

    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
