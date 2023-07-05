package view;

import com.google.gson.Gson;
import controller.GameControllers.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Chat;
import model.DataBase;
import model.Message;
import network.Client;
import network.NotificationReceiver;
import network.Packet;

import java.io.IOException;
import java.util.ArrayList;

public class ChatMenu extends Application {

    private AnchorPane anchorPane = new AnchorPane();
    private VBox vBox = new VBox();

    private TextField newMessage;

    private Timeline timeline;

    private ScrollPane scrollPane = new ScrollPane();
    private String name;

    @Override
    public void start(Stage stage) throws Exception {
        initialize();
        scrollPane.setMaxSize(1000, 600);
        Scene scene = new Scene(anchorPane);
        scrollPane.setLayoutX(10);
        scrollPane.setLayoutY(10);
        anchorPane.getChildren().add(scrollPane);
        addTimeline();
        stage.setScene(scene);
        stage.show();
    }

    private void initialize() {
        vBox = new VBox();
        vBox.setMaxSize(1000, 600);
        setVbox();
        newMessage = new TextField();
        newMessage.setLayoutX(10);
        newMessage.setLayoutY(610);
        anchorPane.getChildren().add(newMessage);
        Button button = new Button("send");
        button.setLayoutX(1010);
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
        back.setLayoutX(1010);
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
                vBox.getChildren().add(hBox);
                hBox.getChildren().add(new Label(message.getUser().getUsername()));
                hBox.getChildren().add(new Label(message.getText()));
            }
            scrollPane.setContent(vBox);
        }
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
