package view;

import controller.GameControllers.ChangeEnvironmentController;
import controller.RegisterAndLoginController;
import controller.UserControllers.ProfileController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.DataBase;

import java.net.URL;
import java.util.Objects;


public class MainMenu extends Application {

    private Stage stage;

    private Scene scene;
    private BorderPane mainPane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        mainPane = FXMLLoader.load(
                new URL(Objects.requireNonNull(RegisterMenu.class.getResource("/fxml/mainMenu.fxml")).toExternalForm()));
        setStyle();
        scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setStyle() {
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
