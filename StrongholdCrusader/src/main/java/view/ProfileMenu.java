package view;

import controller.UserControllers.ProfileController;
import enums.Output;
import enums.Validations;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenu extends Application {
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newNickname;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newSlogan;
    @FXML
    private TextField oldPassword;
    @FXML
    private TextField newPassword;
    

    private ProfileController profileController;

    public ProfileMenu(ProfileController profileController) {
        this.profileController = profileController;
    }

    /*public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        System.out.println("profile menu:");
        while (true) {
            input = scanner.nextLine();
            output = null;
            if (input.matches("show current menu"))
                output = Output.PROFILE_MENU;
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_INFO)) != null) {
                output = changeInfo(matcher);
            } else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.CHANGE_PASSWORD)) != null) {
                output = changePassword(matcher);
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.REMOVE_SLOGAN) != null) {
                output = removeSlogan();
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_HIGHSCORE) != null) {
                System.out.println(displayHighScore());
                continue;
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_RANK) != null) {
                System.out.println(displayRank());
                continue;
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.DISPLAY_SLOGAN) != null) {
                System.out.println(displaySlogan());
                continue;
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY) != null) {
                System.out.println(displayProfile());
                continue;
            } else if (ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.BACK) != null) {
                System.out.println("main menu:");
                return;
            }
            if (output != null) {
                System.out.println(output.getString());
            } else System.out.println("Invalid Command!");
        }
    }*/

    private Output changePassword(Matcher matcher) {
        String oldPassword = Validations.getInfo("o", matcher.group());
        String newPassword = Validations.getInfo("n", matcher.group());
        if (oldPassword == null || newPassword == null) return null;
        return profileController.changePassword(oldPassword, newPassword);
    }

    private Output changeInfo(Matcher matcher) {
        String info = null, flag = null;
        flag = matcher.group("flag");
        if ((info = matcher.group("info")) == null)
            info = matcher.group("info2");
        return profileController.changeInfo(flag, info);
    }
    public void showAlert(Output output) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(output.getString());
        alert.show();
    }

    public void saveNewUsername(MouseEvent mouseEvent) {
        showAlert(profileController.changeUsername(newUsername.getText()));
    }

    public void saveNewNickname(MouseEvent mouseEvent) {
        showAlert(profileController.changeNickname(newNickname.getText()));
    }

    public void saveNewEmail(MouseEvent mouseEvent) {
        showAlert(profileController.changeEmail(newEmail.getText()));
    }

    public void saveNewSlogan(MouseEvent mouseEvent) {
        showAlert(profileController.changeSlogan(newSlogan.getText()));
    }

    public void saveNewPassword(MouseEvent mouseEvent) {
        showAlert(profileController.changePassword(oldPassword.getText(), newPassword.getText()));
    }

    public void removeSlogan(MouseEvent mouseEvent) {
        showAlert(profileController.removeSlogan());
    }

    public void displaySlogan(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(profileController.displaySlogan());
        alert.show();
    }

    public void displayHighScore(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.valueOf(profileController.displayHighScore()));
        alert.show();
    }

    public void displayRank(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(String.valueOf(profileController.displayRank()));
        alert.show();
    }

    public void displayProfile(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(profileController.displayAllProfile());
        alert.show();
    }
//TODO
    public void back(MouseEvent mouseEvent) {
        //RegisterAndLoginController.enterMainMenu(profileController.getCurrentUser().getUsername());
    }

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane pane = FXMLLoader.load(Objects.requireNonNull(ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml")));
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("1", "2", "3");
    }
}
