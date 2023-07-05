package view;

import com.google.gson.Gson;
import enums.ChatType;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Chat;
import model.DataBase;
import model.User;
import network.Client;
import network.NotificationReceiver;
import network.Packet;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class ChatroomMenu extends Application {

    public ChoiceBox rooms;
    public TextArea members;
    public TextField roomName;
    public TextField user;
    private Scene scene;
    @FXML
    private Pane mainPane;

    @Override
    public void start(Stage stage) throws Exception {
        mainPane = FXMLLoader.load(
                new URL(Objects.requireNonNull(LoginMenu.class.getResource("/fxml/chatroomMenu.fxml")).toExternalForm()));

        Scene scene = new Scene(mainPane);
        setBackground();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList();
        for (Chat chat: NotificationReceiver.getChats()) {
            if (chat.getChatType().equals(ChatType.GROUP))
                list.add(chat.getName());
        }
        rooms.setItems(list);
        rooms.setValue("none");
    }

    private void setBackground() {
        mainPane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/5559468.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    public void enterPublicChat() throws Exception {
        ChatMenu chatMenu = new ChatMenu();
        chatMenu.setName("public");
        chatMenu.start(RegisterMenu.getStage());
    }

    public void back() throws Exception {
        (new MainMenu()).start(RegisterMenu.getStage());
    }

    public void enterRoom() throws Exception {
        ChatMenu chatMenu = new ChatMenu();
        chatMenu.setName(rooms.getValue().toString());
        chatMenu.start(RegisterMenu.getStage());
    }

    public void makeNewRoom() throws Exception {
        Scanner scanner = new Scanner(members.getText());
        ArrayList<User> members = new ArrayList<>();
        boolean isPossible = true;
        members.add(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()));
        while (scanner.hasNextLine()) {
            User user = DataBase.getInstance().getUserByUsername(scanner.nextLine());
            if (user == null) isPossible = false;
            else members.add(user);
        }
        if (roomName.getText().isEmpty()) isPossible = false;
        if (isPossible) {
            Chat chat = new Chat();
            chat.setName(roomName.getText());
            chat.setChatType(ChatType.GROUP);
            chat.setMembers(members);
            NotificationReceiver.getChats().add(chat);
            Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
            ChatMenu chatMenu = new ChatMenu();
            chatMenu.setName(roomName.getText());
            chatMenu.start(RegisterMenu.getStage());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("users are invalid or room name is empty");
            alert.show();
        }
    }

    public void enterPrivateChat() throws Exception {
        if (DataBase.getInstance().getUserByUsername(user.getText()) == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("username is invalid");
            alert.show();
        } else {
            StringBuilder chatName = new StringBuilder();
            if (user.getText().compareTo(MainMenu.getUsername()) > 0) {
                chatName.append(MainMenu.getUsername()).append(user.getText());
            } else {
                chatName.append(user.getText()).append(MainMenu.getUsername());
            }
            Chat chat = NotificationReceiver.getChatByName(chatName.toString());
            if (chat == null) {
                chat = new Chat();
                chat.setName(chatName.toString());
                chat.setChatType(ChatType.PRIVATE);
                ArrayList<User> members = new ArrayList<>();
                members.add(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()));
                members.add(DataBase.getInstance().getUserByUsername(user.getText()));
                chat.setMembers(members);
                NotificationReceiver.getChats().add(chat);
                Client.dataOutputStream.writeUTF(new Packet("update chat", (new Gson()).toJson(chat)).toJson());
            }
            ChatMenu chatMenu = new ChatMenu();
            chatMenu.setName(chatName.toString());
            chatMenu.start(RegisterMenu.getStage());
        }
    }
}
