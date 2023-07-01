package view;

import controller.TradeController;
import enums.ImageEnum;
import enums.Output;
import enums.environmentEnums.Material;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.User;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class TradeMenu extends Application {

    private static Stage stage;
    public final String css = Objects.requireNonNull(this.getClass().getResource("/css/style.css")).toExternalForm();
    private TradeController tradeController;

    private BorderPane root = new BorderPane();

    private HBox hBox = new HBox();
    private Button tradeHistory = new Button("Trade History");
    private Button makeRequest = new Button("Make Request");

    private Button back = new Button("Back");

    private Text text = new Text("The business is at your disposal, my Lord");

    private Popup personDetailInfoPopUp;
    private Popup materialInfoPopUp;

    public void setTradeController(TradeController tradeController) {
        this.tradeController = tradeController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        TradeMenu.stage = stage;
        setMainBackground();
        initialize();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    private void setMainBackground() {
        root.setMinSize(1400, 700);
        root.setBackground(new Background(new BackgroundImage(ImageEnum.TRADE_MENU.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }

    private void initialize() throws Exception {

        tradeHistory.setMinSize(100, 100);
        makeRequest.setMinSize(100, 100);

        hBox.getChildren().addAll(tradeHistory, makeRequest);
        hBox.setSpacing(350);

        hBox.setAlignment(Pos.CENTER);
        back.setAlignment(Pos.BOTTOM_CENTER);

        text.setFont(new Font(50));
        text.setFill(Color.DARKGRAY);
        text.setX(300);
        text.setY(250);

        back.setOnAction(ae -> {
            try {
                backToShop();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        makeRequest.setOnAction(ae -> makeRequest());
        tradeHistory.setOnAction(ae -> tradeHistory());

        root.getChildren().add(text);
        root.setCenter(hBox);
        root.setBottom(back);

    }

    private void backToShop() throws Exception {
        new StoreMenu().start(stage);
    }


    private void makeRequest() {

        ArrayList<User> users = tradeController.getGame().getPlayers();

        Popup makeRequest = new Popup();
        BorderPane main = new BorderPane();

        main.setBorder(Border.stroke(Color.BLACK));
        main.setMinSize(700, 700);
        main.setBackground(new Background(new BackgroundImage(ImageEnum.REQUEST.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        VBox vBox = new VBox();

        //TODO --> I'm not sure about users
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(tradeController.getGame().getCurrentUser()))continue;
            Button button = new Button(users.get(i).getUsername());
            int finalI = i;
            button.setOnAction(ae -> showPersonDetails(users.get(finalI)));
            vBox.getChildren().add(button);
        }

        Button back1 = new Button("Back");
        back1.setOnAction(ae -> makeRequest.hide());

        vBox.getChildren().add(back1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);

        main.setCenter(vBox);
        makeRequest.getContent().add(main);
        makeRequest.show(stage);
    }

    private void showPersonDetails(User user) {

        personDetailInfoPopUp = new Popup();
        BorderPane main = new BorderPane();

        main.setBorder(Border.stroke(Color.BLACK));
        main.setMinSize(700, 700);
        main.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        VBox vBox = new VBox();

        ArrayList<Material>materials = user.getGovernance().getGovernanceResource().getOnlineMaterials();

        for(int i = 0 ; i < materials.size(); i++){
            Button materialButton = new Button(materials.get(i).getName());
            int finalI = i;
            materialButton.setOnAction(actionEvent -> showMaterialDetail(materials.get(finalI)));
            vBox.getChildren().add(materialButton);
        }


        Button back1 = new Button("Back");
        back1.setOnAction(ae -> personDetailInfoPopUp.hide());

        vBox.getChildren().add(back1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(3);

        main.setCenter(vBox);
        personDetailInfoPopUp.getContent().add(main);
        personDetailInfoPopUp.show(stage);
    }

    private void showMaterialDetail(Material material){
        personDetailInfoPopUp.hide();
        materialInfoPopUp = new Popup();
        BorderPane main = new BorderPane();

        main.setBorder(Border.stroke(Color.BLACK));
        main.setMinSize(700, 700);
        main.setBackground(new Background(new BackgroundImage(ImageEnum.OLD_PAPER.getImage(),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

        Button back = new Button("Back");
        back.setOnAction(ae -> backToInfo());

        VBox vBox = new VBox();

        HBox hBox1 = new HBox();
        Button increase = new Button("+");
        Button decrease = new Button("-");

        AtomicInteger textFieldNumber = new AtomicInteger();
        TextArea number = new TextArea(String.valueOf(textFieldNumber.get()));
        number.setMaxHeight(50);
        number.setMaxWidth(50);

        hBox1.getChildren().addAll(increase ,number , decrease );
        hBox1.setSpacing(10);

        increase.setOnAction(actionEvent -> {
            textFieldNumber.getAndIncrement();
            number.setText(textFieldNumber.toString());
        });
        decrease.setOnAction(actionEvent -> {
            textFieldNumber.getAndDecrement();
            if(textFieldNumber.get()  < 0 )
                setOutOfRangeError();
            number.setText(textFieldNumber.toString());
        });

        HBox hBox = new HBox();
        RadioButton request = new RadioButton("Request");
        RadioButton donate = new RadioButton("Donate");

        hBox.getChildren().addAll(request,donate);
        hBox.setSpacing(20);

        ImageView imageView = new ImageView(ImageEnum.getImageByName(material.getName()));
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);


        TextArea message = new TextArea();
        message.setMaxWidth(100);
        message.setMaxHeight(100);

        hBox.setAlignment(Pos.CENTER);
        hBox1.setAlignment(Pos.CENTER);

        Button submit = new Button("Submit");
        submit.setAlignment(Pos.CENTER);

        submit.setOnAction(actionEvent -> {
            if(request.isHover()){
                Output output = tradeController.requestTrade(material.getName(),textFieldNumber.get(),material.getSellingPrice() * textFieldNumber.get() ,message.getText());
                setTradeAdded();
            }
            if(donate.isHover()){

            }
        });


        vBox.getChildren().addAll(imageView,hBox1,hBox,submit);
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);
        main.setCenter(vBox);
        back.setAlignment(Pos.CENTER);
        main.setBottom(back);

        materialInfoPopUp.getContent().add(main);
        materialInfoPopUp.show(stage);
    }

    private void setOutOfRangeError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("out of range!");
        alert.showAndWait();
    }

    private void setTradeAdded(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Trade added!");
        alert.showAndWait();
    }
    private void backToInfo(){
        materialInfoPopUp.hide();
        personDetailInfoPopUp.show(stage);
    }
    private void tradeHistory() {
    }

}