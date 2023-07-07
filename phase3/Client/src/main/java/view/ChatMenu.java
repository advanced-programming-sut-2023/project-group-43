package view;

import com.google.gson.Gson;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

    private ChoiceBox choiceBox = new ChoiceBox();
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
                isDeleting = true;
            }
        });
    }



    public void setName(String name) {
        this.name = name;
    }

    private void initialize() throws IOException {
        vBox = new VBox();
        vBox.setMaxSize(1000, 600);
        vBox.setMinSize(1000, 600);
        vBox.setSpacing(10);

        setVbox();
        newMessage = new TextField();
        newMessage.setLayoutX(10);
        newMessage.setLayoutY(610);
        anchorPane.getChildren().add(newMessage);
        choiceBox.setLayoutX(1100);
        choiceBox.setLayoutY(300);
        ObservableList<String> reactions = FXCollections.observableArrayList();
        reactions.addAll("1", "2", "3");
        choiceBox.setItems(reactions);
        choiceBox.setValue("1");
        anchorPane.getChildren().add(choiceBox);
        Button button = new Button("send");
        button.setLayoutX(510);
        button.setLayoutY(610);
        button.setOnMouseClicked(mouseEvent -> {
            Message message = new Message(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()), newMessage.getText());
            Chat chat = NotificationReceiver.getChatByName(name);
            chat.addMessage(message);
            try {
                Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newMessage.setText("");
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

    private void setVbox() throws IOException {
        Chat chat = NotificationReceiver.getChatByName(name);
        synchronized (chat) {
            for (Message message : chat.getMessages()) {
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
                    hBox.getChildren().add(new Label("+"));
                    if (message.isSeen()) hBox.getChildren().add(new Label("+"));
                } else {
                    hBox.setStyle("-fx-background-color: lightpink; -fx-padding: 10px; -fx-border-radius: 5px");
                    hBox.setLayoutX(10);
                    if (!message.isSeen()) {
                        message.setSeen(true);
                        Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
                    }
                    setFunction(hBox, message);
                }
                vBox.getChildren().add(hBox);
                hBox.getChildren().add(new Label(message.getUser().getUsername()));
                hBox.getChildren().add(new Label(message.getText()));
                if (message.getTime() == null)
                    message.setTime("11:12");
                Label label = new Label(message.getTime());
                label.setStyle("-fx-font-size: 8px");
                hBox.getChildren().add(label);
                if (message.getReactionNumber() > 0) {
                    hBox.getChildren().add(addReaction(message));
                }
            }
            scrollPane.setContent(vBox);
        }
    }

    private void setFunction(HBox hBox, Message message) {
        hBox.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                message.setReactionNumber(Integer.parseInt(choiceBox.getValue().toString()));
                Chat chat = NotificationReceiver.getChatByName(name);
                synchronized (chat) {
                    try {
                        Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    private Group addReaction(Message message) {
        StringBuilder avatarPath = new StringBuilder();
        avatarPath.append("/images/emoji/" + message.getReactionNumber() + ".jpg");
        Image image = new Image(Objects.requireNonNull(ProfileMenu.class.getResource(avatarPath.toString())).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setX(1);
        imageView.setY(1);
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setPreserveRatio(true);
        Group root = new Group(imageView);
        return root;
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
                    isDeleting = false;
                    Chat chat = NotificationReceiver.getChatByName(name);
                    synchronized (chat) {
                        int index = -1;
                        for (int i = 0; i < chat.getMessages().size(); i++) {
                            if (chat.getMessages().get(i).getText().equals(chat.getMessages().get(i).getText()) &&
                                    chat.getMessages().get(i).getUser().getUsername().equals(message.getUser().getUsername())) {
                                index = i;
                                break;
                            }
                        }
                        if (index > -1) {
                            chat.getMessages().remove(index);
                            try {
                                Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
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
                    try {
                        setVbox();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

}
