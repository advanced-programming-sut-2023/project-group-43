package view;

import controller.UserControllers.ScoreboardController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ScoreboardMenu extends Application implements Initializable {
    private static ScoreboardController scoreboardController;
    private Stage stage;
    private Scene scene;

    public AnchorPane root;


    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(
                new URL((ScoreboardMenu.class.getResource("/fxml/scoreboardMenu.fxml")).toExternalForm()));
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

    private void setBackground() {
        root.setBackground(new Background(new BackgroundImage(ImageEnum.FRIENDSHIP.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
    }
    public static ScoreboardController getScoreboardController() {
        return scoreboardController;
    }

    public static void setScoreboardController(ScoreboardController scoreboardController) {
        ScoreboardMenu.scoreboardController = scoreboardController;
    }
}
