package view;

import controller.GameControllers.StoreController;
import enums.ImageEnum;
import enums.environmentEnums.Material;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;

public class StoreTable extends Application {

    private static Stage stage;
    public final String css = Objects.requireNonNull(this.getClass().getResource("/css/style.css")).toExternalForm();
    public BorderPane root = new BorderPane();
    public BorderPane center = new BorderPane();
    private final HBox hBox = new HBox();
    private final HBox images = new HBox();
    private String item;
    private StoreController storeController;

    public void setStoreController(StoreController storeController) {
        this.storeController = storeController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StoreTable.stage = stage;
        setBackground();
        initialize();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void setItem(String item) {
        this.item = item;
    }

    private void initialize() {
        ArrayList<Material> materials = Material.getMaterialsByType(item);

        for (Material material : materials) {
            Button button = new Button(material.getName());
            ImageView imageView = new ImageView();
            imageView.setImage(material.getImage());

            imageView.setFitHeight(100);
            imageView.setFitWidth(100);

            button.setMinSize(80, 80);
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
        System.out.println("I am creating pop up");
        Popup popup = new Popup();

        HBox main = new HBox();
        main.setMinSize(200, 200);
        main.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        BorderPane right = new BorderPane();
        ImageView imageView = new ImageView();
        imageView.setImage(ImageEnum.COIN.getImage());
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);
        Label coin = new Label();
        coin.setMinSize(100, 100);
        //TODO  2 MUST CHANGED TO USER DATA BASE
        coin.setText(String.valueOf(2));
        right.setBottom(imageView);
        right.setRight(coin);

        BorderPane left = new BorderPane();
        ImageView pic = new ImageView();
        pic.setImage(material.getImage());
        pic.setFitWidth(80);
        pic.setFitHeight(80);
        Label number = new Label();
        number.setMinSize(100, 100);
        number.setText(String.valueOf(material.getRange()));
        left.setBottom(pic);
        left.setRight(number);

        BorderPane center = new BorderPane();
        VBox vBox = new VBox();
        Button buy = new Button("Buy  " + material.getBuyingPrice());
        Button sell = new Button("Sell " + material.getSellingPrice());
        Button back = new Button("Back");
        buy.setMinWidth(100);
        sell.setMinWidth(100);
        back.setMinWidth(100);
        sell.setOnAction(actionEvent -> sell(material, number, coin));
        buy.setOnAction(actionEvent -> buy(material, number, coin));
        back.setOnAction(actionEvent -> popup.hide());
        vBox.getChildren().addAll(buy, sell, back);
        vBox.setSpacing(60);
        center.setCenter(vBox);

        main.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        main.getChildren().addAll(right, center, left);
        main.setSpacing(40);
        main.setTranslateX(20);

        popup.getContent().add(main);
        popup.show(stage);
    }

    private void buy(Material material, Label number, Label coin) {
        storeController.buy(material.getName(), 1);
        number.setText(String.valueOf(material.getRange()));
        coin.setText("-1");
        //TODO
    }

    private void sell(Material material, Label number, Label coin) {
        storeController.sell(material.getName(), 1);
        number.setText(String.valueOf(material.getRange()));
        coin.setText("-1");
        //TODO
    }

}

