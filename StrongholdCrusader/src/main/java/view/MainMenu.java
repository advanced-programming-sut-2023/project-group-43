package view;

import enums.Output;
import enums.menuEnums.MainMenuCommands;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;

import static view.Menu.scanner;

public class MainMenu extends Application {

    public static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        BorderPane mainPane = FXMLLoader.load(
                new URL(RegisterMenu.class.getResource("/fxml/mainMenu.fxml").toExternalForm()));

        Scene scene = new Scene(mainPane);
        stage.setScene(scene);
        stage.show();
    }

    public void run() throws Exception {
        System.out.println("main menu:");
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.matches("show current menu")) {
                System.out.println(Output.MAIN_MENU.getString());
                return;
            }
            if (input.matches("back")) {
                back();
                return;
            }
            if (MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_PROFILE_MENU) != null) {
                enterProfileMenu();
                return;
            }
            if (MainMenuCommands.getMatcher(input, MainMenuCommands.ENTER_CHANGE_ENVIRONMENT_MENU) != null) {
                enterChangeEnvironmentMenu();
                return;
            }
            System.out.println("Invalid Command!");
        }
    }

    private void back() throws Exception {
        (new LoginMenu()).start(stage);
    }
    private void enterProfileMenu(){
        //TODO sana should fix the controller
        //(new ProfileMenu()).start(stage);
    }

    private void enterChangeEnvironmentMenu() throws Exception {
        (new ChangeEnvironmentMenu()).start(stage);
    }
}
