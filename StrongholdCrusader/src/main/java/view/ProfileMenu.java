package view;

import controller.MainUserController;
import controller.RegisterAndLoginController;
import controller.UserControllers.ProfileController;
import enums.Output;
import enums.Validations;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.DataBase;
import model.User;

import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;

public class ProfileMenu extends Application {
    public User profileMenuCurrentUser;

    private Stage profileMenuStage;
    @FXML
    private TextField newUsername;
    @FXML
    private TextField newNickname;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newSlogan;
    @FXML
    private PasswordField oldPassword;
    @FXML
    private PasswordField newPassword;
    @FXML
    private PasswordField passwordConfirmation;
    public static ProfileController profileController;
    public CheckBox sloganCheckBox;
    public CheckBox randomSlogan;
    public Label sloganError;
    public Label usernameError;
    public Label passwordError;
    public Label passwordConfirmationError;
    public Label emailError;
    public Label nicknameError;
    public TextField passwordRecoveryAnswer;
    public ChoiceBox<String> passwordRecoveryQuestion;
    public TextField passwordAnswerConfirmation;
    public Label questionError;
    public Rectangle captchaRec;
    public TextField captcha;
    private String captchaNumber;
    public HBox userInfo = new HBox();
    public HBox picture = new HBox();
    public ChoiceBox avatar;
    public BorderPane pane;
    public void setProfileController(String username) {
        User currentUser = DataBase.getInstance().getUserByUsername(username);
       profileController = new ProfileController(currentUser);
    }
    @Override
    public void start(Stage stage) throws Exception {
        profileMenuStage = stage;
        BorderPane profilePane = FXMLLoader.load(new URL(ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml").toExternalForm()));
        //BorderPane profilePane = FXMLLoader.load(Objects.requireNonNull(ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml")));
        Scene scene = new Scene(profilePane);
        profileMenuStage.setScene(scene);
        profilePane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
        profileMenuStage.show();
    }
    @FXML
    private void initialize() {
        //ObservableList<String> list = FXCollections.observableArrayList();
        //list.addAll("1", "2", "3", "4");
        //avatar.setItems(list);
        //avatar.setValue(profileMenuCurrentUser.getAvatarNumber());
        newUsername.setText(profileController.getCurrentUser().getUsername());
        newNickname.setText(profileController.getCurrentUser().getNickname());
        newEmail.setText(profileController.getCurrentUser().getEmail());
        if (profileController.getCurrentUser().getSlogan() == null) newSlogan.setText("Slogan is empty");
        else newSlogan.setText(profileController.getCurrentUser().getSlogan());
        /*
        passwordAnswerConfirmation.textProperty().addListener((observable, oldText, newText) -> checkQuestion());
        passwordRecoveryAnswer.textProperty().addListener((observable, oldText, newText) -> checkQuestion());
        new RegisterMenu().generateNewCaptcha();*/
        newUsername.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkUsername(newUsername.getText())) == null)
                usernameError.setText("ok");
            else usernameError.setText(output.getString());
        });
        newNickname.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkNickname(newNickname.getText())) == null)
                nicknameError.setText("ok");
            else nicknameError.setText(output.getString());
        });
        newPassword.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkPassword(newPassword.getText())) == null)
                passwordError.setText("ok");
            else passwordError.setText(output.getString());
        });
        passwordConfirmation.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkPasswordConfirmation(passwordConfirmation.getText(), newPassword.getText())) == null)
                passwordConfirmationError.setText("ok");
            else passwordConfirmationError.setText(output.getString());
        });
        newEmail.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkEmail(newEmail.getText())) == null)
                emailError.setText("ok");
            else emailError.setText(output.getString());
        });
        newSlogan.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkSlogan(newSlogan.getText(), sloganCheckBox.isSelected())) == null)
                sloganError.setText("ok");
            else sloganError.setText(output.getString());
        });
        generateNewCaptcha();
    }
    public void generateNewCaptcha() {
        captchaNumber = RegisterAndLoginController.chooseCaptcha();
        captchaRec.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/captcha/" + captchaNumber + ".png").toExternalForm())));
    }
    public void changeAvatar() {
        profileController.getCurrentUser().setAvatarNumber(Integer.parseInt(avatar.getValue().toString()));
        if (picture.getChildren().size() > 0)
            picture.getChildren().remove(0);
        updateAvatar();
    }
    private void updateAvatar() {
        pane.getChildren().remove(picture);
        picture = new HBox();
        picture.setLayoutX(120);
        picture.setLayoutY(10);
        Rectangle rectangle = new Rectangle(100, 100, 100, 100);
        rectangle.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/image/pic" +
                profileController.getCurrentUser().getAvatarNumber() + ".jpg").toExternalForm())));
        picture.getChildren().add(rectangle);
        pane.getChildren().add(picture);
    }
    private void updateUsername() {
        if (userInfo.getChildren().size() > 0)
            userInfo.getChildren().remove(0);
        pane.getChildren().remove(userInfo);
        userInfo = new HBox();
        Label username = new Label();
        username.setText("username: " + profileController.getCurrentUser().getUsername());
        username.setLayoutX(100);
        username.setLayoutY(100);
        userInfo = new HBox();
        userInfo.setLayoutY(10);
        userInfo.setLayoutX(250);
        userInfo.getChildren().add(username);
        pane.getChildren().add(userInfo);
    }
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
        generateNewCaptcha();
    }
    public void enterProfileMenu() throws Exception {
        String username = profileController.getCurrentUser().getUsername();
        ProfileMenu profileMenu = new ProfileMenu();
        profileMenu.setProfileController(username);
        profileMenu.start(RegisterMenu.getStage());
    }
    public void saveNewUsername(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (usernameError.getText().equals("ok") && captcha.getText().equals(captchaNumber)) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(profileController.changeUsername(newUsername.getText()).getString());
        } else {
            alert.setContentText("enter a new username or enter the captcha correctly!");
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.show();
        try {
            enterProfileMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //showAlert(profileController.changeUsername(newUsername.getText()));
    }

    public void saveNewNickname(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (nicknameError.getText().equals("ok") && captcha.getText().equals(captchaNumber)) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(profileController.changeNickname(newNickname.getText()).getString());
        } else {
            alert.setContentText("enter a new nickname or enter the captcha correctly!");
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.show();
        try {
            enterProfileMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void saveNewEmail(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (emailError.getText().equals("ok") && captcha.getText().equals(captchaNumber)) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(profileController.changeEmail(newEmail.getText()).getString());
        } else {
            alert.setContentText("enter a new email or enter the captcha correctly!");
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.show();
        try {
            enterProfileMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //showAlert(profileController.changeEmail(newEmail.getText()));
    }

    public void saveNewSlogan(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (sloganError.getText().equals("ok") && captcha.getText().equals(captchaNumber)) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(profileController.changeSlogan(newSlogan.getText()).getString());
        } else {
            alert.setContentText("enter a new slogan or enter the captcha correctly!");
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.show();
        try {
            enterProfileMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //showAlert(profileController.changeSlogan(newSlogan.getText()));
    }

    public void saveNewPassword(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        if (newPassword.getText().equals("ok") && passwordConfirmation.getText().equals("ok") && captcha.getText().equals(captchaNumber) && profileController.getCurrentUser().getPassword().equals(oldPassword.getText())) {
            alert.setAlertType(Alert.AlertType.INFORMATION);
            alert.setContentText(profileController.changePassword(oldPassword.getText(), newPassword.getText()).getString());
        } else {
            alert.setContentText("complete the fields correctly or enter the captcha correctly!");
            alert.setAlertType(Alert.AlertType.ERROR);
        }
        alert.show();
        try {
            enterProfileMenu();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //showAlert(profileController.changePassword(oldPassword.getText(), newPassword.getText()));
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
    public void back(MouseEvent mouseEvent) throws Exception {
        //(new MainMenu()).setMainUserController(profileController.getCurrentUser().getUsername());
        (new MainMenu()).start(RegisterMenu.getStage());
    }
    private void checkQuestion() {
        if (!passwordRecoveryAnswer.getText().equals(passwordAnswerConfirmation.getText()))
            questionError.setText("not equal");
        else if (passwordAnswerConfirmation.getText().isEmpty()) questionError.setText("empty field");
        else questionError.setText("ok");
    }
    public void activateSlogan() {
        if (sloganCheckBox.isSelected()) {
            newSlogan.setDisable(false);
            sloganError.setText("empty field!");
        } else {
            newSlogan.setDisable(true);
            newSlogan.setText("");
            sloganError.setText("ok");
        }
    }
    public void chooseRandomSlogan() {
        if (randomSlogan.isSelected()) {
            sloganCheckBox.setSelected(true);
            newSlogan.setDisable(false);
            newSlogan.setText(RegisterAndLoginController.makeRandomSlogan());
        }
    }
}
