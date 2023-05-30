package view;

import controller.StoreController;
import enums.Output;
import enums.Validations;
import enums.menuEnums.StoreMenuCommands;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;

public class StoreMenu extends Application {

    public static Stage stage;
    private StoreController storeController;


    public StoreMenu(StoreController storeController) {
        this.storeController = storeController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        StoreMenu.stage = stage;
        BorderPane storeMenuPane = FXMLLoader.load(new URL(this.getClass().getResource("/fxml/storeMenu.fxml").toExternalForm()));
        Scene scene = new Scene(storeMenuPane);
        stage.setScene(scene);
        stage.show();
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Matcher matcher;
        Output output;
        System.out.println("store menu:");
        while (true) {
            input = scanner.nextLine();
            output = null;
            if (input.matches("show current menu"))
                output = Output.STORE_MENU;
            if (input.matches("back")) {
                storeController.getGame().setSelectedBuilding(null);
                System.out.println("game menu:");
                return;
            } else if (StoreMenuCommands.getMatcher(input, StoreMenuCommands.SHOW_PRICE_LIST) != null) {
                System.out.println(storeController.showPriceList());
                continue;
            } else if ((matcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.BUY)) != null) {
                output = buy(matcher);
            } else if ((matcher = StoreMenuCommands.getMatcher(input, StoreMenuCommands.SELL)) != null) {
                output = sell(matcher);
            }
            if (output == null) System.out.println("invalid command!");
            else System.out.println(output.getString());
        }
    }

    private Output buy(Matcher matcher) {
        String itemName = Validations.getInfo("i", matcher.group());
        String amount = Validations.getInfo("a", matcher.group());
        if (itemName != null && amount != null && amount.matches("\\d+"))
            return storeController.buy(itemName, Integer.parseInt(amount));
        return null;
    }

    private Output sell(Matcher matcher) {
        String itemName = Validations.getInfo("i", matcher.group());
        String amount = Validations.getInfo("a", matcher.group());
        if (itemName != null && amount != null && amount.matches("\\d+"))
            return storeController.sell(itemName, Integer.parseInt(amount));
        return null;
    }
}
