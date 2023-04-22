import model.DataBase;
import view.RegisterMenu;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        RegisterMenu registerMenu = new RegisterMenu();
        registerMenu.run();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    FileOutputStream fileOut = new FileOutputStream("data.json");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(DataBase.getInstance().getUsers());
                    out.close();
                    fileOut.close();
                } catch (IOException ex) {
                }
            }
        }));
    }
}
