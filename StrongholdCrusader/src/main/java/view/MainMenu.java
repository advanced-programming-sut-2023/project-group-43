package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;


public class MainMenu extends Application {

    public static Stage stage;
    private Scene scene;
    private BorderPane mainPane;
    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        mainPane = FXMLLoader.load(
                new URL(RegisterMenu.class.getResource("/fxml/mainMenu.fxml").toExternalForm()));
        setStyle();
        scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }

    private void setStyle(){
    }
    public void back(MouseEvent mouseEvent) throws Exception {
        (new LoginMenu()).start(stage);
    }

    public void help(MouseEvent mouseEvent){

    }
    public void enterProfileMenu(MouseEvent mouseEvent){
        //fix the controller
        //(new ProfileMenu()).start(stage);
    }

    public void enterChangeEnvironmentMenu(MouseEvent mouseEvent) throws Exception {
        //(new ChangeEnvironmentMenu()).start(stage);
    }
}
