import controller.MainController;
import model.DataBase;
import view.MainMenu;
import view.RegisterMenu;

public class Main {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                DataBase.getInstance().saveData();
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
