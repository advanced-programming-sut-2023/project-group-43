package view;

import controller.RegisterAndLoginController;
import model.DataBase;

import java.util.regex.Matcher;

public class RegisterMenu extends Menu{

    private RegisterAndLoginController registerAndLoginController;

    public RegisterMenu(DataBase dataBase) {
        super(dataBase);
        this.registerAndLoginController = new RegisterAndLoginController(dataBase);
    }
    public void run(){
    }

    private String createUser(Matcher matcher){return null;}

    public void enterLoginMenu() {}
}
