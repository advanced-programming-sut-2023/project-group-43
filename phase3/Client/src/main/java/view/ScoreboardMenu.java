package view;

import controller.UserControllers.ScoreboardController;
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

import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.DataBase;
import model.User;
import model.tableInfo.ScoreboardCell;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ScoreboardMenu extends Application implements Initializable {
    private static ScoreboardController scoreboardController;
    private Stage stage;
    private Scene scene;

    public AnchorPane root;

    public TableView table ;
    public TableColumn<ScoreboardCell, ImageView> avatar;
    public TableColumn<ScoreboardCell, Integer> rank;
    public TableColumn<ScoreboardCell, String> username;
    public TableColumn<ScoreboardCell, Integer> score;
    public TableColumn<ScoreboardCell, Circle> state;
    public TableColumn<ScoreboardCell, String> lastSeen;
    public TableColumn<ScoreboardCell, Button> friendship;
    public Button back;

    private final ObservableList<ScoreboardCell> scoreboardTable = FXCollections.observableArrayList();


    @Override
    public void start(Stage stage) throws Exception {
        root = FXMLLoader.load(
                new URL((ScoreboardMenu.class.getResource("/fxml/scoreboardMenu.fxml")).toExternalForm()));
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

    private void setBackground() {
        root.setBackground(new Background(new BackgroundImage(ImageEnum.FRIENDSHIP.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
    }
    public static ScoreboardController getScoreboardController() {
        return scoreboardController;
    }

    public static void setScoreboardController(ScoreboardController scoreboardController) {
        ScoreboardMenu.scoreboardController = scoreboardController;
    }

    private void setTable(){
        username.setCellValueFactory(new PropertyValueFactory<ScoreboardCell , String>("username"));
        score.setCellValueFactory(new PropertyValueFactory<ScoreboardCell, Integer>("score"));
        state.setCellValueFactory(new PropertyValueFactory<ScoreboardCell , Circle>("online"));
        avatar.setCellValueFactory(new PropertyValueFactory<ScoreboardCell , ImageView>("avatar"));
        friendship.setCellValueFactory(new PropertyValueFactory<ScoreboardCell , Button>("friendship"));
        lastSeen.setCellValueFactory(new PropertyValueFactory<ScoreboardCell , String>("last seen"));
        rank.setCellValueFactory(new PropertyValueFactory<ScoreboardCell , Integer>("avatar"));
    }

    private void addRows(){
        clearCells();
        ArrayList<User> users = DataBase.getInstance().scoreboard();
        System.out.println( "Score board :" + users.size());
        //TODO -> size should be change to a proper number
        for(int i = 0 ; i < users.size() ; i++){
            scoreboardTable.add(new ScoreboardCell(users.get(i)));
        }
        table.setItems(scoreboardTable);
    }

    private void clearCells(){
        scoreboardTable.clear();
        table.getItems().clear();
    }
    private void enterMainMenu() throws Exception {
        new MainMenu().start(RegisterMenu.getStage());
    }
}
