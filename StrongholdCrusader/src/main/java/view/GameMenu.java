package view;

import controller.GameControllers.GameController;
import controller.GameControllers.GovernanceController;
import controller.GameControllers.StoreController;
import controller.TradeController;
import enums.ImageEnum;
import enums.Output;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Cell;
import model.DataBase;
import model.Game;
import model.MiniBar;
import model.buildings.Building;

public class GameMenu extends Application {

    private Stage stage;
    private Scene scene;

    private static GameController gameController;
    private int turns, numberOfPlayers;
    private String x, y;


    private AnchorPane root = new AnchorPane();
    private AnchorPane anchorPane = new AnchorPane();
    private ScrollBar scrollBar = new ScrollBar();

    private int size = 50;
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
        addMiniBar();
        setButtons();
        setCells();
        dragAndDropBuildingOnMap();
        gameController.initializeGame();
    }

    private void addMiniBar() {
        MiniBar miniBar = new MiniBar();
        Pane pane = miniBar.getPane();
        pane.setLayoutY(500);
        anchorPane.getChildren().add(pane);
    }

    private void setButtons() {
        addButton(anchorPane);
        Rectangle up = new Rectangle();
        Rectangle down = new Rectangle();
        Rectangle right = new Rectangle();
        Rectangle left = new Rectangle();
        Rectangle plus = new Rectangle();
        Rectangle minus = new Rectangle();
        addDirectionButton(up, "up", 600, 10);
        addDirectionButton(down, "down", 600, 600);
        addDirectionButton(right, "right", 1200, 300);
        addDirectionButton(left, "back", 10, 300);
        addDirectionButton(plus, "plus", 10,10);
        addDirectionButton(minus, "minus", 10, 70);
        addFunctions(up, down, right, left, plus, minus);
    }

    private void setRootPane() {
        root.setMinSize(1500, 600);
    }

    private void addFunctions(Rectangle up, Rectangle down, Rectangle right, Rectangle left, Rectangle plus, Rectangle minus) {
        down.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ((600 / size) - yPosition < gameController.getGame().getColumn()) {
                    yPosition -= 1;
                    resetCells();
                }
            }
        });
        up.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (yPosition < 0) yPosition += 1;
                resetCells();
            }
        });
        left.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (xPosition < 0) xPosition += 1;
                resetCells();
            }
        });
        right.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if ((1200 / size) - xPosition < gameController.getGame().getRow()) xPosition -= 1;
                resetCells();
            }
        });
        plus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (size < 100) size *= 2;
                resetCells();
            }
        });
        minus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (size > 25) size /= 2;
                resetCells();
            }
        });
    }

    private void resetCells() {
        anchorPane.getChildren().removeAll(anchorPane.getChildren());
        root.getChildren().removeAll(root.getChildren());
        anchorPane.getChildren().add(root);
        root.setMaxHeight(800);
        root.setMaxWidth(800);
        initialize();
    }

    private void addDirectionButton(Rectangle rectangle, String address, int x, int y) {
        rectangle.setWidth(50);
        rectangle.setHeight(50);
        rectangle.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/face_mask/" + address + ".png").toExternalForm())));
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
        //temporary button for trade menu
        Button tradeMenu = new Button("trade menu");
        tradeMenu.setLayoutX(1200);
        tradeMenu.setLayoutY(500);
        root.getChildren().add(tradeMenu);
        tradeMenu.setOnAction(ae -> {
            try {
                enterTradeMenu();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
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
    public void showAlert(Output output) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(output.getString());
        alert.show();
    }
    private void setCells() {
        for (int x = 0; x < gameController.getGame().getRow(); x++) {
            for (int y = 0; y < gameController.getGame().getColumn(); y++) {
                if (x < gameController.getGame().getRow() && y < gameController.getGame().getColumn()) {
                    GridPane cell = loadCell(gameController.getGame().getCells()[x][y]);
                    setCell(cell, size * (x + xPosition), size * (y + yPosition));
                    int finalX = x;
                    int finalY = y;
                    cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText(gameController.cellInfo(gameController.getGame().getCells()[finalX][finalY]));
                            alert.show();
                        }
                    });
                }
            }
        }
    }
    private void dragAndDropBuildingOnMap() {
        gameController.getMiniBar().addListenerToFindTheSelectedBuilding();
        for (int i = 0; i < gameController.getGame().getRow(); i++) {
            for (int j = 0; j < gameController.getGame().getColumn(); j++) {
                GridPane cell = loadCell(gameController.getGame().getCells()[i][j]);
                int finalX = i;
                int finalY = j;
                cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if (gameController.getMiniBar().selectedBuildingName != null) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText(gameController.dropBuilding(finalX + 1, finalY + 1, gameController.getMiniBar().selectedBuildingName).getString());
                            alert.show();
                        }
                    }
                });
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
        if (i >= 0 && i < 1200 && j >= 0 && j < 600) {
            cell.setLayoutX(i);
            cell.setLayoutY(j);
            root.getChildren().add(cell);
        }
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
