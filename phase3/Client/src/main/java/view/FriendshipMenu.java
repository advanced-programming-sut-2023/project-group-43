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
    public TableView table;
    public TableColumn<FriendshipCell,String> username;
    public TableColumn<FriendshipCell,Integer> score;
    public TableColumn<FriendshipCell,String> slogan;
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
        state.setCellValueFactory(new PropertyValueFactory<FriendshipCell , Button>("friendship"));
        avatar.setCellValueFactory(new PropertyValueFactory<FriendshipCell , ImageView>("avatar"));
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
