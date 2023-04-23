package view;

import controller.GameController;
import controller.RegisterAndLoginController;
import controller.StoreController;
import enums.Output;
import enums.menuEnums.GameMenuCommands;
import enums.menuEnums.GovernanceMenuCommands;
import model.DataBase;

import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{

    private GameController gameController;

    public GameMenu(GameController gameController) {
        this.gameController = gameController;
    }

    public void run() {
        Scanner scanner = Menu.getScanner();
        String input;
        Output output;
        Matcher matcher;
        System.out.println("game menu:");
        while (true){
            input = scanner.nextLine();
            output = null;
            //enter menu part
            if(GameMenuCommands.getMatcher(input, GameMenuCommands.ENTER_CHANGE_ENVIRONMENT_MENU) != null) {
                System.out.println(enterChangeEnvironmentMenu());
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_STORE_MENU)!= null) {
                System.out.println(enterStoreMenu());
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_TRADE_MENU)!= null) {
                System.out.println(enterTradeMenu());
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_GOVERNANCE_MENU)!= null) {
                System.out.println(enterGovernmentMenu());
            }
            //game
            if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.SELECT_BUILDING))!= null) {
                System.out.println(selectBuilding(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.CREATE_UNIT))!= null) {
                System.out.println(createUnit(matcher));
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.REPAIR)!= null) {
                System.out.println();
            }
            else if((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_UNIT))!= null) {
                System.out.println(selectUnit(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.MOVE_UNIT))!= null) {
                System.out.println(moveUnit(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.SET_UNITS_STATE))!= null) {
                System.out.println(setUnitState(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.DIRECT_ATTACK))!= null) {
                System.out.println(aerialAttack(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.AERIAL_ATTACK))!= null) {
                System.out.println(directAttack(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.POUR_OIL))!= null) {
                System.out.println(pourOil(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.DIG_TUNNEL))!= null) {
                System.out.println(digTunnel(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.BUILD_EQUIPMENT))!= null) {
                System.out.println(buildEquipment(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.DISBAND_UNIT))!= null) {
                System.out.println(disbandUnit(matcher));
            }
            else System.out.println("Invalid Command!");
        }
    }

    public String enterChangeEnvironmentMenu() {
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        changeEnvironmentMenu.run();
        return Output.ENTER_CHANGE_ENVIRONMENT_MENU.getString();
    }
    public String enterStoreMenu() {
        StoreMenu storeMenu = new StoreMenu();
        storeMenu.run();
        return Output.ENTER_STORE_MENU.getString();
    }
    private String enterTradeMenu() {
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.run();
        return Output.ENTER_TRADE_MENU.getString();
    }
    private String enterGovernmentMenu() {
        GovernanceMenu governanceMenu = new GovernanceMenu();
        governanceMenu.run();
        return Output.ENTER_GOVERNANCE_MENU.getString();
    }

    private String repair(){

    }

    private String selectBuilding(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return gameController.selectBuilding(x,y);
    }

    private String createUnit(Matcher matcher) {
        String type = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        return gameController.createUnit(type,count);
    }

    private String selectUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return gameController.selectUnit(x,y);
    }

    private String moveUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return gameController.moveUnit(x,y);
    }

    private String patrolUnit(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return gameController.moveUnit(x,y);
    }

    private String disbandUnit(Matcher matcher){
        //TODO
        return gameController.disbandUnit();
    }

    private String setUnitState(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String state = matcher.group("state");
        return gameController.setUnitState(x,y,state);
    }

    private String aerialAttack(Matcher matcher) {
        return gameController.
    }

    private String directAttack(Matcher matcher){return null;}

    private String pourOil(Matcher matcher) {
        return null;
    }

    private String digTunnel(Matcher matcher) {
        return null;
    }

    private String buildEquipment(Matcher matcher) {
        return null;
    }


}
