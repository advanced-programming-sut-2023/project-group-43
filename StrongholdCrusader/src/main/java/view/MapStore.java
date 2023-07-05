package view;

import controller.GameControllers.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.User;
import java.util.Map;

public class MapStore extends Application {
    public User currentUser;
    public Stage stage;
    public Scene scene;
    public Pane pane = new Pane();
    private ScrollPane scrollPane = new ScrollPane();
    private HBox hBox = new HBox();
    @Override
    public void start(Stage stage) throws Exception {
        this.pane.setMinSize(1600, 200);
        this.scrollPane.setMaxSize(1000, 250);
        hBox.setMaxSize(1000, 250);
        setAllMiniMaps();
        pane.getChildren().add(scrollPane);
        setBackground(hBox);
        scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setFullScreen(true);
        addListener();
        //initialize();
        stage.show();
    }
    private void setBackground(HBox pane) {
        pane.setBackground(new Background(new BackgroundImage(new Image(this.getClass().getResource("/images/background/mapStore.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
    }

    public void initialize() {
        addListener();
    }
    public void setAllMiniMaps() {
        final int[] i = {0};
        GameController.getAllMaps().forEach((key, value) -> {
            value.setLayoutY(i[0] * 100);
            value.setLayoutY(50);
            hBox.getChildren().add(value);
            i[0]++;
        });
    }
    public void addListener() {
        GameController.getAllMaps().forEach((key, value) -> {
            value.setOnMouseClicked(mouseEvent -> {
                Map.Entry<User,String> entry = key.entrySet().iterator().next();
                User user = entry.getKey();
                String mapName = entry.getValue();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("owner : " + user.getUsername() + "\nmapName : " + mapName);
                alert.show();
                MapTransfer mapTransfer = new MapTransfer(user, mapName, value);
            });
        });
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    /*public static void setDefaultMaps(int row, int column) {
        System.out.println("this is row " + row);
        System.out.println("this is column " + column);
        Cell[][] cells = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells[i][j] = new Cell();
                cells[i][j].setX(i);
                cells[i][j].setY(j);
                if (i % 9 == 0) cells[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 1) cells[i][j].setTexture(Texture.GRAVEL_GROUND);
                else if ((i % 9) == 2) cells[i][j].setTexture(Texture.BOULDER);
                else if ((i % 9) == 3) cells[i][j].setTexture(Texture.ROCK);
                else if ((i % 9) == 4) cells[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 5) cells[i][j].setTexture(Texture.GRASS);
                else if ((i % 9) == 6) cells[i][j].setTexture(Texture.MEADOW);
                else if ((i % 9) == 7) cells[i][j].setTexture(Texture.DENSE_GRASSLAND);
                else cells[i][j].setTexture(Texture.PLAIN);
            }
        }
        completeMap(column, cells, Texture.OIL, Texture.SHALLOW_WATER, Texture.RIVER, Texture.SMALL_POND, Texture.BIG_POND, Texture.BEACH);
        defaultMaps.put("default1", cells);
        Cell[][] cells2 = new Cell[row][column];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                cells2[i][j] = new Cell();
                if ((i % 9) == 0) cells2[i][j].setTexture(Texture.GRAVEL_GROUND);
                else if ((i % 9) == 1) cells2[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 2) cells2[i][j].setTexture(Texture.ROCK);
                else if ((i % 9) == 3) cells2[i][j].setTexture(Texture.BOULDER);
                else if ((i % 9) == 4) cells2[i][j].setTexture(Texture.GRASS);
                else if ((i % 9) == 5) cells2[i][j].setTexture(Texture.GROUND);
                else if ((i % 9) == 6) cells2[i][j].setTexture(Texture.DENSE_GRASSLAND);
                else if ((i % 9) == 7) cells2[i][j].setTexture(Texture.PLAIN);
                else cells2[i][j].setTexture(Texture.MEADOW);
            }
        }
        completeMap(column, cells2, Texture.BEACH, Texture.GROUND, Texture.GROUND, Texture.RIVER, Texture.SHALLOW_WATER, Texture.OIL);
        defaultMaps.put("default2", cells2);
    }
    private static void completeMap(int column, Cell[][] cells, Texture oil, Texture shallowWater, Texture river, Texture smallPond, Texture bigPond, Texture beach) {
        for (int i = 0; i < column; i++) {
            if ((i % 9) == 0) cells[0][i].setTexture(oil);
            else if ((i % 9) == 1) cells[0][i].setTexture(shallowWater);
            else if ((i % 9) == 2) cells[0][i].setTexture(river);
            else if ((i % 9) == 3) cells[0][i].setTexture(smallPond);
            else if ((i % 9) == 4) cells[0][i].setTexture(bigPond);
            else if ((i % 9) == 5) cells[0][i].setTexture(beach);
            else if ((i % 9) == 6) {
                cells[0][i].setTexture(Texture.SEA);
                break;
            }
        }
    }*/
}
