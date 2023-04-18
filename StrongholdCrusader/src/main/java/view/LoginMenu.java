package view;

import controller.RegisterAndLoginController;
import model.DataBase;

import java.util.regex.Matcher;

public class LoginMenu extends Menu{

    private RegisterAndLoginController registerAndLoginController;

    public LoginMenu(DataBase dataBase) {
        super(dataBase);
        this.registerAndLoginController = new RegisterAndLoginController(dataBase);
    }

    public void run(){}

    private String loginUser(Matcher matcher){return null;}

    private String forgetPassword(Matcher matcher){return null;}

    private void enterMainMenu() {}

}
