package view;

import controller.UserControllers.FriendshipController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

public class FriendshipMenu extends Application implements Initializable {
    private static FriendshipController friendshipController;

    private Stage stage;
    private Scene scene;

    public AnchorPane root;
    public TableView table;
    public TableColumn rank;
    public TableColumn username;
    public TableColumn score;
    public TableColumn state;
    public TableColumn lastSeen;
    public TableColumn friendship;

    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(
                new URL((FriendshipMenu.class.getResource("/fxml/friendshipMenu.fxml")).toExternalForm()));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBackground();
    }
    public static FriendshipController getFriendshipController() {
        return friendshipController;
    }

    public static void setFriendshipController(FriendshipController friendshipController) {
        FriendshipMenu.friendshipController = friendshipController;
    }


    private void setBackground() {
        root.setBackground(new Background(new BackgroundImage(ImageEnum.FRIENDSHIP.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
    }


}
