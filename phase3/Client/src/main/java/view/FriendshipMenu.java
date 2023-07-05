package view;

import controller.UserControllers.FriendshipController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.DataBase;
import model.User;
import model.tableInfo.FriendshipCell;


import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class FriendshipMenu extends Application implements Initializable {
    private static FriendshipController friendshipController;
    private ArrayList<User> users;

    private Stage stage;
    private Scene scene;

    public AnchorPane root;
    public TableView<FriendshipCell> table;
    public TableColumn<FriendshipCell, String> username;
    public TableColumn<FriendshipCell, Integer> score;
    public TableColumn<FriendshipCell, String> slogan;
    public TableColumn<FriendshipCell, Button> state;
    public TableColumn<FriendshipCell, ImageView> avatar;
    public Button back;

    private final ObservableList<FriendshipCell> friendshipTable = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(
                new URL((Objects.requireNonNull(FriendshipMenu.class.getResource("/fxml/friendshipMenu.fxml"))).toExternalForm()));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBackground();
        setTable();
        back.setOnAction(actionEvent -> {
            try {
                enterMainMenu();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        addRows();
        table.setOnMousePressed(ae -> {
            createPopup();
        });
    }


    private void createPopup() {
        System.out.println("create pop up");
        if(table.getSelectionModel().getSelectedItem() != null) {
            User friendUser = DataBase.getInstance().getUserByUsername(table.getSelectionModel().getSelectedItem().getUsername());
            makeRequestPopup(friendshipController.getCurrentUser() , friendUser);
        }
    }
    private void makeRequestPopup(User currentUser , User friendUser) {
        Popup popup = new Popup();
        BorderPane borderPane = new BorderPane();
        borderPane.setMinSize(200,200);
        borderPane.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
        Button back = new Button("Back");
        borderPane.setBottom(back);
        back.setOnAction(ae -> popup.hide());

        if (DataBase.getInstance().isFriend(currentUser, friendUser)) {
            Text text = new Text("Friend");
            borderPane.setCenter(text);
            popup.getContent().add(borderPane);
            popup.show(RegisterMenu.getStage());
            return;
        }
        if(DataBase.getInstance().hasRequest(currentUser , friendUser)){
            Button accept = new Button("Accept");
            Button decline = new Button("Decline");
            borderPane.setLeft(accept);
            borderPane.setRight(decline);
            popup.getContent().add(borderPane);
            popup.show(RegisterMenu.getStage());
            return;
        }
        Button sendRequest = new Button("Send Request");
        borderPane.setCenter(sendRequest);
        popup.getContent().add(borderPane);
        popup.show(RegisterMenu.getStage());
    }

    public static FriendshipController getFriendshipController() {
        return friendshipController;
    }

    public static void setFriendshipController(FriendshipController friendshipController) {
        FriendshipMenu.friendshipController = friendshipController;
    }


    private void setBackground() {
        root.setBackground(new Background(new BackgroundImage(ImageEnum.FRIENDSHIP.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
    }

    private void setTable(){
        username.setCellValueFactory(new PropertyValueFactory<FriendshipCell , String>("username"));
        slogan.setCellValueFactory(new PropertyValueFactory<FriendshipCell , String>("slogan"));
        score.setCellValueFactory(new PropertyValueFactory<FriendshipCell , Integer>("score"));
        //avatar.setCellValueFactory(new PropertyValueFactory<FriendshipCell , ImageView>("avatar"));
    }

    private void addRows(){
        users = DataBase.getInstance().getUsers();
        clearCells();
        //TODO -> size should change to proper number
        System.out.println("this is users size" + users.size());
        for (User user : users) {
            friendshipTable.add(new FriendshipCell(friendshipController.getCurrentUser(), user));
        }
        table.setItems(friendshipTable);
    }

    private void clearCells(){
        table.getItems().clear();
        friendshipTable.clear();
    }

    private void enterMainMenu() throws Exception {
        new MainMenu().start(RegisterMenu.getStage());
    }
}
