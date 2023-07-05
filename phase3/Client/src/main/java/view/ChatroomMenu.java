package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class ChatroomMenu extends Application {

    private Scene scene;
    @FXML
    private Pane mainPane;

    @Override
    public void start(Stage stage) throws Exception {
        mainPane = FXMLLoader.load(ChatroomMenu.class.getResource("/fxml/chatroomMenu.fxml"));
        scene = new Scene(mainPane);
        setBackground();
        stage.setScene(scene);
        stage.show();
    }

    private void setBackground() {
        mainPane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/5559468.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    public void enterPublicChat() {

    }
}
