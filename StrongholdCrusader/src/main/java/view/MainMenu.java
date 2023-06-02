package view;

import controller.GameControllers.ChangeEnvironmentController;
import controller.RegisterAndLoginController;
import controller.UserControllers.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.DataBase;

import java.net.URL;
import java.util.Objects;


public class MainMenu extends Application {

    private Stage stage;

    private Scene scene;
    @FXML
    private Pane mainPane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mainPane = FXMLLoader.load(
                new URL(Objects.requireNonNull(RegisterMenu.class.getResource("/fxml/mainMenu.fxml")).toExternalForm()));
        scene = new Scene(mainPane);
        setBackground();
        stage.setScene(scene);
        stage.show();
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
        loginMenu.setLoginController(registerAndLoginController);
        loginMenu.start(stage);
    }


    public void enterProfileMenu() throws Exception {
        ProfileController profileController = new ProfileController(DataBase.getInstance().findLoggedInUser());
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.setProfileController(profileController);
        profileMenu.start(stage);
    }

    public void enterChangeEnvironmentMenu() throws Exception {
        ChangeEnvironmentController changeEnvironmentController = new ChangeEnvironmentController(DataBase.getInstance().findLoggedInUser());
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        changeEnvironmentMenu.setChangeEnvironmentController(changeEnvironmentController);
        changeEnvironmentMenu.start(stage);
    }
}
