package view;

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

import java.net.URL;
import java.util.regex.Matcher;

public class ProfileMenu extends Application {

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
    private PasswordField passwordConfirmation;
    private ProfileController profileController;
    public CheckBox sloganCheckBox;
    public Label usernameError;
    public Label passwordError;
    public Label passwordConfirmationError;
    public Label sloganError;
    public Label emailError;
    public Label nicknameError;
    public TextField passwordRecoveryAnswer;
    public ChoiceBox<String> passwordRecoveryQuestion;
    public TextField passwordAnswerConfirmation;
    public Label questionError;
    public CheckBox randomSlogan;
    public Rectangle captchaRec;
    public TextField captcha;
    private String captchaNumber;
    public HBox userInfo = new HBox();
    public HBox picture = new HBox();
    public ChoiceBox avatar;
    public BorderPane pane;
    public void setProfileController(ProfileController profileController) {
        this.profileController = profileController;
    }
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("1");
        profileMenuStage = stage;
        System.out.println("2");
        BorderPane profilePane = FXMLLoader.load(new URL(ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml").toExternalForm()));
        //pane = FXMLLoader.load(Objects.requireNonNull(ProfileMenu.class.getResource("/fxml/ProfileMenu.fxml")));
        System.out.println("3");
        Scene scene = new Scene(profilePane);
        System.out.println("4");
        profileMenuStage.setScene(scene);
        System.out.println("5");
        profilePane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/profileMenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
        System.out.println("6");
        profileMenuStage.show();
    }
    @FXML
    private void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("1", "2", "3", "4");
        avatar.setItems(list);
        avatar.setValue(profileController.getCurrentUser().getAvatarNumber());
        newUsername.setText(profileController.getCurrentUser().getUsername());
        newUsername.setDisable(true);
        newUsernameListener();
        newNickname.setText(profileController.getCurrentUser().getNickname());
        newNicknameListener();
        newNickname.setDisable(true);
        newEmail.setText(profileController.getCurrentUser().getEmail());
        newEmailListener();
        newEmail.setDisable(true);
        if (profileController.getCurrentUser().getSlogan().equals("")) newSlogan.setText("Slogan is empty");
        else newSlogan.setText(profileController.getCurrentUser().getSlogan());
        newSlogan.setDisable(true);
        newSloganListener();
        oldPassword.setText(profileController.getCurrentUser().getPassword());
        newPassword.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkPassword(newPassword.getText())) == null)
                passwordError.setText("ok");
            else passwordError.setText(output.getString());
        });
        passwordConfirmation.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkPasswordConfirmation(passwordConfirmation.getText(),
                    newPassword.getText())) == null)
                passwordConfirmationError.setText("ok");
            else passwordConfirmationError.setText(output.getString());
        });
        passwordAnswerConfirmation.textProperty().addListener((observable, oldText, newText) -> checkQuestion());
        passwordRecoveryAnswer.textProperty().addListener((observable, oldText, newText) -> checkQuestion());
        new RegisterMenu().generateNewCaptcha();
    }
    public void newUsernameListener() {
        newUsername.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkUsername(newUsername.getText())) == null)
                usernameError.setText("ok");
            else usernameError.setText(output.getString());
        });
    }
    public void newNicknameListener() {
        newNickname.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkNickname(newNickname.getText())) == null)
                nicknameError.setText("ok");
            else nicknameError.setText(output.getString());
        });
    }
    public void newEmailListener() {
        newEmail.textProperty().addListener((observable, oldText, newText) -> {
        Output output;
        if ((output = RegisterAndLoginController.checkEmail(newEmail.getText())) == null)
            emailError.setText("ok");
        else emailError.setText(output.getString());
    });
    }
    public void newSloganListener() {
        newSlogan.textProperty().addListener((observable, oldText, newText) -> {
            Output output;
            if ((output = RegisterAndLoginController.checkSlogan(newSlogan.getText(), sloganCheckBox.isSelected())) == null)
                sloganError.setText("ok");
            else sloganError.setText(output.getString());
        });
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
    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start(profileMenuStage);
    }
    private void checkQuestion() {
        if (!passwordRecoveryAnswer.getText().equals(passwordAnswerConfirmation.getText()))
            questionError.setText("not equal");
        else if (passwordAnswerConfirmation.getText().isEmpty()) questionError.setText("empty field");
        else questionError.setText("ok");
    }
}
