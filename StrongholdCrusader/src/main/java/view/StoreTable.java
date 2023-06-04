package view;

import controller.GameControllers.StoreController;
import enums.ImageEnum;
import enums.environmentEnums.Material;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.DataBase;

import java.net.URL;
import java.util.ArrayList;

public class StoreTable extends Application {

    private static Stage stage;
    public BorderPane root = new BorderPane();
    public BorderPane center = new BorderPane();
    private Scene scene;
    private HBox hBox = new HBox();
    private HBox images = new HBox();
    public final String css = this.getClass().getResource("/css/style.css").toExternalForm();
    private String item;
    private ArrayList<Material> materials;
    private StoreController storeController;

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StoreTable.stage = stage;
        setBackground();
        initialize();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void setItem(String item) {
        this.item = item;
    }

    private void initialize() {
        materials = Material.getMaterialsByType(item);
        ArrayList<Button> buttons = new ArrayList<>();

        for (Material material : materials) {
            Button button = new Button(material.getName());
            ImageView imageView = new ImageView();
            imageView.setImage(material.getImage());

            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            button.setMinSize(80, 80);
            buttons.add(button);
            button.setOnAction(actionEvent -> createPopUp(material));

            hBox.getChildren().add(button);
            images.getChildren().add(imageView);
        }
        Button back = new Button("Back");
        back.setOnAction(ae -> {
            try {
                new StoreMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        images.setSpacing(60);
        hBox.setSpacing(50);


        center.setMaxSize(1000, 200);
        center.setTop(images);
        center.setBottom(hBox);

        root.setCenter(center);
        root.setBottom(back);
    }


    private void setBackground() {
        root.setMinSize(1550, 800);
        root.setBackground(new Background(new BackgroundImage(ImageEnum.WOOD_MENU.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
    }

    private void createPopUp(Material material) {
        Popup popup = new Popup();

        HBox main = new HBox();
        root.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        BorderPane right = new BorderPane();
        ImageView imageView = new ImageView();
        imageView.setImage(ImageEnum.COIN.getImage());
        Label coin = new Label();
        coin.setText(String.valueOf(DataBase.getInstance().findLoggedInUser().getGovernance().getGold()));
        right.setCenter(imageView);
        right.setBottom(coin);

        BorderPane left = new BorderPane();
        ImageView pic = new ImageView();
        pic.setImage(material.getImage());
        Label number = new Label();
        number.setText(String.valueOf(material.getRange()));
        left.setCenter(pic);
        left.setBottom(number);

        BorderPane center = new BorderPane();
        VBox vBox = new VBox();
        Button buy = new Button("Buy  " + material.getBuyingPrice());
        Button sell = new Button("Sell " + material.getSellingPrice());
        Button back = new Button("Back") ;
        sell.setOnAction(actionEvent -> sell(material));
        buy.setOnAction(actionEvent -> buy(material));
        back.setOnAction(actionEvent -> popup.hide());
        vBox.getChildren().addAll(buy, sell, back);
        center.setCenter(vBox);

        main.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        main.getChildren().addAll(right, left, center);
    }

    private void buy(Material material){

    }

    private void sell(Material material){}

}

