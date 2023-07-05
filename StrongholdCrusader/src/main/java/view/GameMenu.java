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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Cell;
import model.DataBase;
import model.MiniBar;
import model.User;
import model.pannels.Barrack;
import model.pannels.EngineerGuild;
import model.pannels.MercenaryPost;
import model.units.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class GameMenu extends Application {

    private Stage stage;
    private Scene scene;


    private boolean isUnitSelected = false;
    private boolean isUnitTargetSelected = false;
    private boolean isPouringOil = false;

    private boolean isCopying = false, isPasting = false;

    private String clipboard;

    private static GameController gameController;
    private int turns;
    private boolean isAttacking = false;
    private int x, y;

    private boolean isAnyPanelOpen = false;

    private boolean isCellSelected = false;


    private AnchorPane root = new AnchorPane();
    private AnchorPane anchorPane = new AnchorPane();
    private ScrollBar scrollBar = new ScrollBar();

    private MercenaryPost mercenaryPost = new MercenaryPost();
    private EngineerGuild engineerGuild = new EngineerGuild();
    private Barrack barrack = new Barrack();

    private int size = 50;
    private int xPosition = 0;
    private int yPosition = 0;

    private boolean isAirAttacking;

    private int firstX, firstY;

    private Label label = new Label();


    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        anchorPane.getChildren().add(root);
        root.setMaxHeight(800);
        root.setMaxWidth(800);
        initialize();
        scene = new Scene(anchorPane);
        gameController.initializeGame();
        label.setLayoutY(200);
        label.setLayoutX(1210);
        anchorPane.getChildren().add(label);
        label.setText(gameController.getGame().getCurrentPlayer().getUsername() + " is playing");
        engineerGuild.addListenerToFindUnit(gameController);
        barrack.addListenerToFindUnit(gameController);
        mercenaryPost.addListenerToFindUnit(gameController);
        stage.setScene(scene);
        setSceneOnKeyBoardPress(scene);
        stage.show();
    }

    private void setSceneOnKeyBoardPress(Scene scene) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.S) {
                isUnitSelected = true;
            } else if (event.getCode() == KeyCode.M) {
                isUnitTargetSelected = true;
            } else if (event.getCode() == KeyCode.A) {
                isAttacking = true;
            } else if (event.getCode() == KeyCode.B) {
                isAirAttacking = true;
            } else if (event.getCode() == KeyCode.P) {
                isPouringOil = true;
            } else if (event.getCode() == KeyCode.C) {
                isCopying = true;
            } else if (event.getCode() == KeyCode.V) {
                isPasting = true;
            }
        });
    }

    @FXML
    public void initialize() {
        setRootPane();
        addMiniBar();
        setButtons();
        setCells();
        //gameController.illness();
        //gameController.updateIllness();
        dragAndDropBuildingOnMap();
    }

    private void addMiniMap(Pane map) {
        anchorPane.getChildren().add(map);
    }
    private Pane miniMap() {
        Pane map = new Pane();
        map.setMaxSize(100, 100);
        map.setLayoutX(1200);
        map.setLayoutY(500);
        for (int i = 0; i < gameController.getGame().getRow(); i++) {
            for(int j = 0; j < gameController.getGame().getColumn(); j++) {
                GridPane cell = loadCell(gameController.getGame().getCells()[i][j], 1);
                cell.setLayoutX(i);
                cell.setLayoutY(j);
                map.getChildren().add(cell);
            }
        }
        return map;
    }

    public static void setGameController(GameController gameController) {
        GameMenu.gameController = gameController;
    }

    private void addMiniBar() {
        MiniBar miniBar = new MiniBar();
        GameController.setMiniBar(miniBar);
        Pane pane = miniBar.getPane();
        pane.setLayoutY(560);
        anchorPane.getChildren().add(pane);
        Pane engineerPane = engineerGuild.getPane();
        engineerPane.setLayoutX(1200);
        engineerPane.setLayoutY(50);
        Pane barrackPane = barrack.getPane();
        barrackPane.setLayoutY(50);
        barrackPane.setLayoutX(1200);
        Pane mercenaryPostPane = mercenaryPost.getPane();
        mercenaryPostPane.setLayoutX(1200);
        mercenaryPostPane.setLayoutY(50);
        anchorPane.getChildren().addAll(barrackPane, engineerPane, mercenaryPostPane);
        engineerGuild.getPane().setVisible(false);
        mercenaryPost.getPane().setVisible(false);
        barrack.getPane().setVisible(false);
    }

    private void setButtons() {
        addButton(anchorPane);
        Rectangle up = new Rectangle();
        Rectangle down = new Rectangle();
        Rectangle right = new Rectangle();
        Rectangle left = new Rectangle();
        Rectangle plus = new Rectangle();
        Rectangle minus = new Rectangle();
        Rectangle back = new Rectangle();
        Rectangle saveMap = new Rectangle();
        addDirectionButton(up, "up", 600, 10);
        addDirectionButton(down, "down", 600, 600);
        addDirectionButton(right, "right", 1200, 300);
        addDirectionButton(left, "back", 10, 300);
        addDirectionButton(plus, "plus", 10, 10);
        addDirectionButton(minus, "minus", 10, 70);
        addDirectionButton(back, "backButton", 1200, 10);
        addDirectionButton(saveMap, "save", 1200, 70);
        addFunctions(up, down, right, left, plus, minus, back, saveMap);
    }

    private void setRootPane() {
        root.setMinSize(1500, 600);
    }

    private void addFunctions(Rectangle up, Rectangle down, Rectangle right, Rectangle left, Rectangle plus, Rectangle minus, Rectangle back, Rectangle saveMap) {
        down.setOnMouseClicked(mouseEvent -> {
            if ((600 / size) - yPosition < gameController.getGame().getColumn()) {
                yPosition -= 1;
                resetCells();
            }
        });
        up.setOnMouseClicked(mouseEvent -> {
            if (yPosition < 0) yPosition += 1;
            resetCells();
        });
        left.setOnMouseClicked(mouseEvent -> {
            if (xPosition < 0) xPosition += 1;
            resetCells();
        });
        right.setOnMouseClicked(mouseEvent -> {
            if ((1200 / size) - xPosition < gameController.getGame().getRow()) xPosition -= 1;
            resetCells();
        });
        plus.setOnMouseClicked(mouseEvent -> {
            if (size < 100) size *= 2;
            resetCells();
        });
        minus.setOnMouseClicked(mouseEvent -> {
            if (size > 25) size /= 2;
            resetCells();
        });
        back.setOnMouseClicked(mouseEvent -> {
            try {
                (new ChangeEnvironmentMenu()).start(RegisterMenu.getStage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        saveMap.setOnMouseClicked(mouseEvent -> {
            StringBuilder sb = new StringBuilder();
            sb.append(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()).getUsername()).append(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()).getMapsOfThisUser().size() + 1);
            DataBase.getInstance().getUserByUsername(MainMenu.getUsername()).AddToMapsOfThisUser(sb.toString(), miniMap());
            HashMap<User, String> hs = new HashMap<>();
            hs.put(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()), sb.toString());
            gameController.addToAllMaps(DataBase.getInstance().getUserByUsername(MainMenu.getUsername()), sb.toString(), miniMap());
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
        addMiniMap(miniMap());
        Rectangle button = new Rectangle();
        button.setWidth(200);
        button.setHeight(200);
        button.setFill(new ImagePattern(new Image(RegisterMenu.class.getResource("/images/game_menu/man.png").toExternalForm())));
        button.setLayoutX(1000);
        button.setLayoutY(500);
        button.setOnMouseClicked(mouseEvent -> {
            try {
                enterGovernmentMenu();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        root.getChildren().add(button);

        //temporary button for next person
        Button nextPerson = new Button();
        nextPerson.setText("next person");
        nextPerson.setLayoutX(1200);
        nextPerson.setLayoutY(650);
        nextPerson.setOnAction(ae -> {
            try {
                goToNextPerson();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        root.getChildren().add(nextPerson);
    }

    private void goToNextPerson() throws Exception {
        gameController.goToNextPerson();
        label.setText(gameController.getGame().getCurrentPlayer().getUsername() + " is playing");
        if (gameController.getGame().getCurrentPlayer().equals(gameController.getGame().getPlayers().get(0))) {
            gameController.applyChanges();
            resetCells();
            turns--;
            if (gameController.isGameEnded() || turns <= 0) {
                endGame();
            }
        }
    }

    private void endGame() throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(gameController.showGameResult());
        alert.show();
        back();
    }

    private void back() throws Exception {
        if (isAnyPanelOpen) {
            isAnyPanelOpen = false;
            engineerGuild.getPane().setVisible(false);
            barrack.getPane().setVisible(false);
            mercenaryPost.getPane().setVisible(false);
        } else
            gameController.enterMainMenu();
    }

    private void setCells() {
        for (int x = 0; x < gameController.getGame().getRow(); x++) {
            for (int y = 0; y < gameController.getGame().getColumn(); y++) {
                if (x < gameController.getGame().getRow() && y < gameController.getGame().getColumn()) {
                    GridPane cell = loadCell(gameController.getGame().getCells()[x][y], size);
                    setCell(cell, size * (x + xPosition), size * (y + yPosition), x, y);
                    gameController.getMiniBar().addListenerToFindTheSelectedBuilding();

                }
            }
        }
    }

    private void setCellFunctions(GridPane cell, int finalX, int finalY) {
        cell.setOnMousePressed(mouseEvent -> {
            if (!isCellSelected) {
                isCellSelected = true;
                firstY = finalY;
                firstX = finalX;
            }
        });

        cell.setOnMouseMoved(mouseEvent -> {
            x = finalX;
            y = finalY;
        });
        cell.setOnMouseClicked(mouseEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (isUnitSelected) {
                isUnitSelected = false;
                alert.setContentText(gameController.selectUnit(finalX + 1, finalY + 1).getString());
            }
            else if (isUnitTargetSelected) {
                isUnitTargetSelected = false;
                alert.setContentText(gameController.moveUnit(finalX + 1, finalY + 1).getString());
            }
            else if (isAttacking) {
                isAttacking = false;
                alert.setContentText(gameController.attackToEnemy(finalX + 1, finalY + 1).getString());
            }
            else if (isAirAttacking) {
                isAirAttacking = false;
                alert.setContentText(gameController.airAttack(finalX + 1, finalY + 1).getString());
            }
            else if (isPouringOil) {
                isPouringOil = false;
                alert.setContentText(gameController.pourOil("up").getString());
            }
            else if (isCopying) {
                isCopying = false;
                if (gameController.getGame().getCells()[finalX][finalY].getBuilding() != null) {
                    clipboard = gameController.getGame().getCells()[finalX][finalY].getBuilding().getName();
                    alert.setContentText("copied!");
                }
                else {
                    alert.setContentText("no building to copy!");
                }
            }
            else if (isPasting) {
                isPasting = false;
                alert.setContentText(gameController.dropBuilding(finalX + 1, finalY + 1, clipboard).getString());
            }
            else if (gameController.getMiniBar().selectedBuildingName == null) {
                alert.setContentText(gameController.cellInfo(gameController.getGame().getCells()[finalX][finalY]));
                gameController.selectBuilding(finalX + 1, finalY + 1);
                gameController.dropUnit(finalX + 1, finalY + 1, 1);
            } else {
                alert.setContentText(gameController.dropBuilding(finalX + 1, finalY + 1, gameController.getMiniBar().selectedBuildingName).getString());
                gameController.getMiniBar().selectedBuildingName = null;
            }
            resetCells();
            try {
                checkSelectBuilding();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            alert.show();
        });
        cell.setOnMouseReleased(mouseEvent -> {
            isCellSelected = false;
            if ((firstX != x || firstY != y))
                showAllCells(firstX, firstY, x, y);
        });
    }

    private void checkSelectBuilding() throws Exception {
        if (gameController.getGame().getSelectedBuilding() != null) {
            String name = gameController.getGame().getSelectedBuilding().getName();
            System.out.println(name);
            if (name.equals("market")) enterStoreMenu();
            else if (name.equals("barrack")) enterBarrack();
            else if (name.equals("engineer guild")) enterEngineerGuild();
            else if (name.equals("mercenary post")) enterMercenaryPost();
        }
    }

    private void enterMercenaryPost() {
        isAnyPanelOpen = true;
        mercenaryPost.getPane().setVisible(true);
    }

    private void enterEngineerGuild() {
        isAnyPanelOpen = true;
        engineerGuild.getPane().setVisible(true);
    }

    private void enterBarrack() {
        isAnyPanelOpen = true;
        barrack.getPane().setVisible(true);
    }

    private void showAllCells(int firstX, int firstY, int finalX, int finalY) {
        if (firstX > finalX) {
            int temp = firstX;
            firstX = finalX;
            finalX = temp;
        }
        if (firstY > finalY) {
            int temp = firstY;
            firstY = finalY;
            finalY = temp;
        }
        String content = gameController.allCellsInfo(firstX, firstY, finalX, finalY);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(content);
        alert.show();
    }

    private void dragAndDropBuildingOnMap() {
        gameController.getMiniBar().addListenerToFindTheSelectedBuilding();
        System.out.println(">" + gameController.getMiniBar().selectedBuildingName + "<");
        if (gameController.getMiniBar().selectedBuildingName != null) {
            System.out.println("it is not null");
            for (int i = 0; i < gameController.getGame().getRow(); i++) {
                for (int j = 0; j < gameController.getGame().getColumn(); j++) {
                    GridPane cell = loadCell(gameController.getGame().getCells()[i][j], 1);
                    int finalX = i;
                    int finalY = j;
                    cell.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent mouseEvent) {
                            System.out.println("this cell is touched to in dragAndDrop");
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText(gameController.dropBuilding(finalX + 1, finalY + 1, gameController.getMiniBar().selectedBuildingName).getString());
                            alert.show();
                        }
                    });
                }
            }
        }
    }

    private GridPane loadCell(Cell cell, int size) {
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

        textureImageview.setFitHeight(size);
        textureImageview.setFitWidth(size);
        textureImageview.setImage(texture);

        item.setFitWidth(0.8 * size);
        item.setFitHeight(0.8 * size);

        if (building != null)
            item.setImage(building);
        else if (cell.getUnits().size() > 0) {
            for (Image image: getUnit(cell))
                item.setImage(image);
        }

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

    private ArrayList<Image> getUnit(Cell cell) {
        ArrayList<Image> units = new ArrayList<>();
        for (Unit unit: cell.getUnits()) {
            units.add(ImageEnum.getImageByName(unit.getName()));
        }
        return units;
    }

    //ignore tunnel
    private void setCell(GridPane cell, int i, int j, int x, int y) {
        if (i >= 0 && i < 1200 && j >= 0 && j < 600) {
            cell.setLayoutX(i);
            cell.setLayoutY(j);
            root.getChildren().add(cell);
            setCellFunctions(cell, x, y);
        }
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }


    public void enterStoreMenu() throws Exception {
        System.out.println("enter store menu");
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
        GovernanceController governanceController = new GovernanceController(gameController.getGame().getCurrentPlayer(), gameController.getGame());
        GovernanceMenu governanceMenu = new GovernanceMenu();
        governanceMenu.setGovernanceController(governanceController);
        governanceMenu.start(RegisterMenu.getStage());
    }
}
