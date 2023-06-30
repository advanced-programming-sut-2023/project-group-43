package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GovernanceController;
import controller.GameControllers.StoreController;
import controller.TradeController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Cell;

public class GameMenu extends Application {

    private Stage stage;
    private Scene scene;

    private static GameController gameController;
    private int turns, numberOfPlayers;
    private String x, y;


    private AnchorPane root = new AnchorPane();
    private AnchorPane anchorPane = new AnchorPane();
    private ScrollBar scrollBar = new ScrollBar();

    private int size = 100;
    private int xPosition = 0;
    private int yPosition = 0;

    public GameController getGameController() {
        return gameController;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        anchorPane.getChildren().add(root);
        root.setMaxHeight(800);
        root.setMaxWidth(800);
        initialize();
        scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {
        setRootPane();
        setCells();
        gameController.initializeGame();
    }

    private void setRootPane() {
        root.setMinSize(1500, 600);
        addButton(anchorPane);
        Rectangle up = new Rectangle();
        Rectangle down = new Rectangle();
        Rectangle right = new Rectangle();
        Rectangle left = new Rectangle();
        addDirectionButton(up, "up", 600, 10);
        addDirectionButton(down, "down", 600, 600);
        addDirectionButton(right, "right", 1200, 400);
        addDirectionButton(left, "back", 10, 400);
    }

    private void addDirectionButton(Rectangle rectangle, String address, int x, int y) {
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/face_mask/" + address +".png").toExternalForm())));
        rectangle.setLayoutX(x);
        rectangle.setLayoutY(y);
        anchorPane.getChildren().add(rectangle);
    }

    private void addButton(AnchorPane root) {
        Rectangle button = new Rectangle();
        button.setWidth(200);
        button.setHeight(200);
        button.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/game_menu/man.png").toExternalForm())));
        button.setLayoutX(1000);
        button.setLayoutY(500);
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
        root.getChildren().add(button);
    }

//    private void setCells() {
//        for (int i = 0; i < 15; i++) {
//            i = -100;
//            j += 100;
//            for (int j = 0; j < 30; j++) {
//                i += 100;
//                if (j < gameController.getGame().getColumn() && i < gameController.getGame().getRow()) {
//                    GridPane cell = loadCell(gameController.getGame().getCells()[i][j]);
//                    setCell(cell);
//                }
//            }
//        }
//    }


    private void setCells() {
        for (int x = 0; x < gameController.getGame().getRow(); x++) {
            for (int y = 0; y < gameController.getGame().getColumn(); y++) {
                if (x < gameController.getGame().getRow() && y < gameController.getGame().getColumn()) {
                    GridPane cell = loadCell(gameController.getGame().getCells()[x][y]);
                    setCell(cell, x * size + xPosition, y * size + yPosition);
                }
            }
        }
    }

    private GridPane loadCell(Cell cell) {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(size, size);

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
    private void setCell(GridPane cell, int i, int j) {
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
        GovernanceController governanceController = new GovernanceController(getGameController().getGame().getCurrentPlayer(), gameController.getGame());
        GovernanceMenu governanceMenu = new GovernanceMenu();
        governanceMenu.setGovernanceController(governanceController);
        governanceMenu.start(RegisterMenu.getStage());
    }


}
