package view;

import controller.GameControllers.ChangeEnvironmentController;
import controller.GameControllers.GameController;
import controller.MainUserController;
import controller.RegisterAndLoginController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DataBase;
import model.User;
import network.NotificationReceiver;

import java.net.URL;
import java.util.Objects;


public class MainMenu extends Application {

    private Stage stage;

    private Scene scene;
    @FXML
    private Pane mainPane;


    private static String username;
    private MainUserController mainUserController;
    private User mainMenuCurrentUser;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        MainMenu.username = username;
    }

    public User getMainMenuCurrentUser() {
        return mainMenuCurrentUser;
    }

    public void setMainMenuCurrentUser(User mainMenuCurrentUser) {
        this.mainMenuCurrentUser = mainMenuCurrentUser;
    }

    public void setMainUserController(String username) {
        MainMenu.username = username;
        User currentUser = DataBase.getInstance().getUserByUsername(username);
        mainUserController = new MainUserController(currentUser);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mainPane = FXMLLoader.load(
                new URL(Objects.requireNonNull(RegisterMenu.class.getResource("/fxml/mainMenu.fxml")).toExternalForm()));
        scene = new Scene(mainPane);
        setBackground();
        stage.setScene(scene);
        addTimeline();
        stage.show();
    }

    private void addTimeline() {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    if (NotificationReceiver.getData() != null && NotificationReceiver.getData().equals("game")) {
                            GameController gameController = new GameController(NotificationReceiver.getGame());
                            NotificationReceiver.getGame().setCurrentUser(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()));
                            gameController.initializeGame();
                            GameMenu gameMenu = new GameMenu();
                            gameMenu.setGameController(gameController);
                            gameMenu.setTurns(NotificationReceiver.getGame().getTurns());
                            NotificationReceiver.setData(null);
                        try {
                            gameMenu.start(RegisterMenu.getStage());
                        } catch (Exception ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                })
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }




    private void setBackground() {
        mainPane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/5559468.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    public void help() {

    }

    public void back() throws Exception {
        RegisterAndLoginController registerAndLoginController = new RegisterAndLoginController();
        LoginMenu loginMenu = new LoginMenu();
        //loginMenu.setLoginController(registerAndLoginController);
        loginMenu.start(RegisterMenu.getStage());
    }


    public void enterProfileMenu() throws Exception {
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.setProfileController(username);
        profileMenu.start(RegisterMenu.getStage());
    }

    public void enterChangeEnvironmentMenu() throws Exception {
        ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController(DataBase.getInstance().getUserByUsername(MainMenu.username));
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        changeEnvironmentMenu.setChangeEnvironmentController(changeEnvironmentController);
        changeEnvironmentMenu.start(RegisterMenu.getStage());
    }
}
