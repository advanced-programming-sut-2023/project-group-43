package view;

import controller.GameControllers.StoreController;
import controller.StoreController;
import enums.ImageEnum;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Objects;

public class StoreMenu extends Application {


    private static Stage stage;
    private Pane storeMenuPane;
    private StoreController storeController;

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
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

    private void setMainBackground() {
        storeMenuPane.setBackground(new Background(new BackgroundImage(ImageEnum.SHOP_MENU.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    public void setFoodOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("food");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }

    public void setWeaponOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("weapon");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }

    public void setToolOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("tool");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }
    public void setMineralOnTable() throws Exception {
        StoreTable storeTable = new StoreTable();
        storeTable.setItem("mineral");
        storeTable.setStoreController(storeController);
        storeTable.start(stage);
    }
    public void back() {
        //TODO
    }
}
