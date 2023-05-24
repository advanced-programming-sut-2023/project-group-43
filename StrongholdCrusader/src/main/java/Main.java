import controller.*;
import model.*;
import view.*;
import view.RegisterMenu;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                DataBase.getInstance().saveData();
                System.exit(0);
            }
        }));
        if (DataBase.getInstance().findLoggedInUser() == null)
            (new RegisterMenu()).run();
        else {
            MainController mainController = new MainController(DataBase.getInstance().findLoggedInUser());
            (new MainMenu(mainController)).run();
        }
    }
}
