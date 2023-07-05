package view;

import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Chat;
import model.DataBase;
import model.Message;
import model.User;
import network.Client;
import network.NotificationReceiver;
import network.Packet;

import java.io.IOException;
import java.util.Objects;

public class ChatMenu extends Application {

    private AnchorPane anchorPane = new AnchorPane();
    private VBox vBox = new VBox();

    private TextField newMessage;

    private Timeline timeline;

    private ScrollPane scrollPane = new ScrollPane();
    private String name;

    private boolean isEditing = false, isDeleting = false;

    @Override
    public void start(Stage stage) throws Exception {
        initialize();
        scrollPane.setMaxSize(1100, 600);
        scrollPane.setMinSize(1100, 600);
        scrollPane.setStyle("-fx-background-color: #939191");
        Scene scene = new Scene(anchorPane);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(10);
        anchorPane.getChildren().add(scrollPane);
        addTimeline();
        stage.setScene(scene);
        setSceneOnKeyBoardPress(scene);
        stage.show();
    }


    private void setSceneOnKeyBoardPress(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.E) {
                isEditing = true;
            } else if (event.getCode() == KeyCode.D) {
                isEditing = true;
            }
        });
    }



    public void setName(String name) {
        this.name = name;
    }

    private void initialize() {
        vBox = new VBox();
        vBox.setMaxSize(1000, 600);
        vBox.setMinSize(1000, 600);
        vBox.setSpacing(10);
        setVbox();
        newMessage = new TextField();
        newMessage.setLayoutX(10);
        newMessage.setLayoutY(610);
        anchorPane.getChildren().add(newMessage);
        Button button = new Button("send");
        button.setLayoutX(510);
        button.setLayoutY(610);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Message message = new Message(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()), newMessage.getText());
                Chat chat = NotificationReceiver.getChatByName(name);
                chat.addMessage(message);
                try {
                    Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        anchorPane.getChildren().add(button);
        Button back = new Button("back");
        back.setLayoutX(510);
        back.setLayoutY(660);
        back.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    timeline.pause();
                    new MainMenu().start(RegisterMenu.getStage());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        anchorPane.getChildren().add(back);
    }

    private void setVbox() {
        synchronized (NotificationReceiver.getChatByName(name)) {
            for (Message message : NotificationReceiver.getChatByName(name).getMessages()) {
                HBox hBox = new HBox();
                hBox.setSpacing(20);
                hBox.setMinWidth(300);
                hBox.setMaxWidth(300);
                hBox.setMinHeight(40);
                hBox.getChildren().add(addAvatar(message.getUser()));
                if (message.getUser().getUsername().equals(MainMenu.getUsername())) {
                    hBox.setStyle("-fx-background-color: lightblue; -fx-padding: 10px; -fx-border-radius: 5px");
                    hBox.setLayoutX(690);
                    setFunctions(hBox, message);
                } else {
                    hBox.setStyle("-fx-background-color: lightpink; -fx-padding: 10px; -fx-border-radius: 5px");
                    hBox.setLayoutX(10);
                }
                vBox.getChildren().add(hBox);
                hBox.getChildren().add(new Label(message.getUser().getUsername()));
                hBox.getChildren().add(new Label(message.getText()));
            }
            scrollPane.setContent(vBox);
        }
    }

    private void setFunctions(HBox hBox, Message message) {
        hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (isEditing) {
                    isEditing = false;
                    message.setText(newMessage.getText());
                    Chat chat = NotificationReceiver.getChatByName(name);
                    synchronized (chat) {
                        try {
                            Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    newMessage.setText("");
                } else if (isDeleting) {
                    Chat chat = NotificationReceiver.getChatByName(name);
                    synchronized (chat) {
                        for (Message m : NotificationReceiver.getChatByName(name).getMessages()) {
                            if (m.getText().equals(m.getText()) && m.getUser().getUsername().equals(message.getUser().getUsername())) {
                                chat.getMessages().remove(m);
                                try {
                                    Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                                break;
                            }
                        }
                    }
                }
            }
        });
    }

    private Group addAvatar(User user) {
        StringBuilder avatarPath = new StringBuilder();
        avatarPath.append("/images/avatar/" + user.getAvatarNumber() + ".png");
        Image image = new Image(Objects.requireNonNull(ProfileMenu.class.getResource(avatarPath.toString())).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setX(1);
        imageView.setY(1);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        return root;
    }

    private void addTimeline() {
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    vBox.getChildren().removeAll(vBox.getChildren());
                    setVbox();
                })
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

}
