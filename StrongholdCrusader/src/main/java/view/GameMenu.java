package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GovernanceController;
import controller.GameControllers.StoreController;
import controller.TradeController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Cell;
import model.DataBase;
import model.Game;

public class GameMenu extends Application {

    private Stage stage;
    private Scene scene;

    private static GameController gameController = new GameController(new Game());
    private int turns, numberOfPlayers;
    private String x, y;


    private AnchorPane root = new AnchorPane();
    private ScrollBar scrollBar = new ScrollBar();

    int i = -100;
    int j = -100;


    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        initialize();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void initialize() {
        setRootPane();
        setScrollBar();
        setCells();
    }

    private void setRootPane() {
        root.setMinSize(1500, 600);
        Button button = new Button();
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                try {
                    enterGovernmentMenu();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        button.setText("governance menu");
        root.getChildren().add(button);
    }

    private void setScrollBar() {
        scrollBar.setMinSize(1000, 700);
        scrollBar.setValue(10);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setUnitIncrement(12);
        scrollBar.setBlockIncrement(10);
        root.getChildren().add(scrollBar);
    }

    private void setCells() {
        for (int i = 0; i < 15; i++) {
            i = -100;
            j += 100;
            for (int j = 0; j < 30; j++) {
                i += 100;
                if (j < gameController.getGame().getColumn() && i < gameController.getGame().getRow()) {
                    GridPane cell = loadCell(gameController.getGame().getCells()[i][j]);
                    setCell(cell);
                }
            }
        }
    }

    private GridPane loadCell(Cell cell) {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(100, 100);

        Image texture = getTexture(cell);
        Image building = null;
        Image rock = null;

        if (cell.getBuilding() != null)
            building = getBuilding(cell);

        if (cell.HasRock())
            rock = getRock(cell);


        ImageView textureImageview = new ImageView(texture);
        ImageView item = new ImageView();

        textureImageview.setFitHeight(100);
        textureImageview.setFitWidth(100);
        textureImageview.setImage(texture);

        item.setFitWidth(80);
        item.setFitHeight(80);

        if (building != null)
            item.setImage(building);

        if (rock != null)
            item.setImage(rock);

        gridPane.getChildren().add(textureImageview);

        if (item.getImage() != null)
            gridPane.getChildren().add(item);


        return gridPane;
    }

    private Image getTexture(Cell cell) {
        Image texture;
        texture = ImageEnum.getImageByName(cell.getTexture().getName());
        return texture;
    }

    private Image getBuilding(Cell cell) {
        Image building;
        building = ImageEnum.getImageByName(cell.getBuilding().getName());
        return building;
    }

    private Image getTree(Cell cell) {
        Image tree;
        tree = ImageEnum.TREE.getImage();
        return tree;
    }

    private Image getRock(Cell cell) {
        Image rock;
        rock = ImageEnum.ROCK.getImage();
        return rock;
    }

    //ignore tunnel
    private void setCell(GridPane cell) {
        cell.setLayoutX(i);
        cell.setLayoutY(j);
        root.getChildren().add(cell);
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    public void enterStoreMenu(String name) throws Exception {
        //TODO--> what is usage of name?
        StoreController storeController = new StoreController(gameController.getGame(), gameController);
        StoreMenu storeMenu = new StoreMenu();
        storeMenu.setStoreController(storeController);
        storeMenu.start(stage);
    }

    private void enterTradeMenu() throws Exception {
        TradeController tradeController = new TradeController(gameController.getGame());
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.setTradeController(tradeController);
        tradeMenu.start(stage);
    }

    private void enterGovernmentMenu() throws Exception {
        GovernanceController governanceController = new GovernanceController(getGameController().getGame().getCurrentUser(), gameController.getGame());
        GovernanceMenu governanceMenu = new GovernanceMenu();
        governanceMenu.setGovernanceController(governanceController);
        governanceMenu.start(stage);
    }


}
