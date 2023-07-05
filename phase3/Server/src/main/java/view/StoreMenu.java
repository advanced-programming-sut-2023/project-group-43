package view;

import controller.GameControllers.GameController;
import controller.GameControllers.StoreController;
import controller.TradeController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class StoreMenu extends Application {


    private static Stage stage;
    private Pane storeMenuPane;
    private static StoreController storeController;

    public Button weapon;
    public Button tool;
    public Button food ;
    public Button mineral;
    public Button enter ;
    public Button back ;

    public void setStoreController(StoreController storeController) {
        StoreMenu.storeController = storeController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StoreMenu.stage = stage;
        storeMenuPane = FXMLLoader.load(new URL(Objects.requireNonNull(this.getClass().getResource("/fxml/storeMenu.fxml")).toExternalForm()));
        setMainBackground();
        Scene mainScene = new Scene(storeMenuPane);
        stage.setScene(mainScene);
        stage.show();
    }

    @FXML
    public void initialize(){
        setButton();
    }


    private void setButton(){

        weapon.setOnAction(actionEvent -> {
            try {
                setWeaponOnTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        food.setOnAction(actionEvent -> {
            try {
                setFoodOnTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        tool.setOnAction(actionEvent -> {
            try {
                setToolOnTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        mineral.setOnAction(actionEvent -> {
            try {
                setMineralOnTable();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        enter.setOnAction(actionEvent -> {
            try {
                enterTradeMenu();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        back.setOnAction(actionEvent -> {
            try {
                back();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void setMainBackground() {
        storeMenuPane.setBackground(new Background(new BackgroundImage(ImageEnum.SHOP_MENU.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    private void setFoodOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("food");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }

    private void setWeaponOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("weapon");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }

    private void setToolOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("tool");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }
    private void setMineralOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("mineral");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }
    @FXML
    private void back() throws Exception {
        System.out.println("inside back");
        GameController gameController = new GameController(StoreController.getGame());
        GameMenu gameMenu = new GameMenu();
        GameMenu.setGameController(gameController);
        gameMenu.start(StoreMenu.stage);
    }

    private void enterTradeMenu() throws Exception {
        TradeController tradeController = new TradeController(storeController.getGame());
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.setTradeController(tradeController);
        tradeMenu.start(stage);
    }
}
