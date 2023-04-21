package view;

import controller.GameController;
import controller.ProfileController;
import model.DataBase;

import java.util.regex.Matcher;

public class ProfileMenu extends Menu{

    private ProfileController profileController;
    public void run(){}

    private String changeProfile(Matcher matcher){return null;}

    private String changePassword(Matcher matcher){return null;}

    private static String changeUsername(Matcher matcher) {return null;}

    private static String changeNickname(Matcher matcher) {return null;}

    private static String changeEmail(Matcher matcher) {return null;}

    private static String changeSlogan(Matcher matcher) {return null;}

    private String removeSlogan(Matcher matcher){return null;}

<<<<<<< Updated upstream
    private String displayProfile(Matcher matcher){return null;}
=======
        return profileController.removeSlogan();
    }
    private Output displayHighScore() {
        return profileController.displayHighScore();
    }
    private Output displayRank() {
        return profileController.displayRank();
    }
>>>>>>> Stashed changes

    private static String displaySlogan(Matcher matcher) {return null;}
}
