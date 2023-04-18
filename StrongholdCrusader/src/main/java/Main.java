import model.DataBase;
import view.RegisterMenu;

public class Main {
    public static void main(String[] args) {
        DataBase dataBase = DataBase.getInstance();
        RegisterMenu registerMenu = new RegisterMenu(dataBase);
        registerMenu.run();
    }
}
