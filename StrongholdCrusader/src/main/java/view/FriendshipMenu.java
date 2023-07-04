package view;

import controller.UserControllers.FriendshipController;
import javafx.application.Application;
import javafx.stage.Stage;

public class FriendshipMenu extends Application {
    private static FriendshipController friendshipController;
    @Override
    public void start(Stage stage) throws Exception {

    }

    public static FriendshipController getFriendshipController() {
        return friendshipController;
    }

    public static void setFriendshipController(FriendshipController friendshipController) {
        FriendshipMenu.friendshipController = friendshipController;
    }
}
