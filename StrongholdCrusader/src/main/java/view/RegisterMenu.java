package view;

import controller.RegisterAndLoginController;
import enums.Output;
import enums.menuEnums.RegisterAndLoginCommands;
import model.DataBase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu extends Menu{

    public void run(){
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        while (true) {
            input = scanner.nextLine();
        }
    }

    private String createUser(Matcher matcher){return null;}

    public void enterLoginMenu() {

    }
}
