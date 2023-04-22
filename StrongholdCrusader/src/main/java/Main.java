import com.google.gson.Gson;
import model.DataBase;
import view.RegisterMenu;

public class Main {
    public static void main(String[] args) {
        RegisterMenu registerMenu = new RegisterMenu();
        registerMenu.run();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                DataBase.getInstance().saveData();
            }
        }));
    }
}
