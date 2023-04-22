package view;

import controller.GameController;
import controller.RegisterAndLoginController;
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
            if(GameMenuCommands.getMatcher(input,GameMenuCommands.SELECT_BUILDING)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.CREATE_UNIT)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.REPAIR)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_UNIT)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.MOVE_UNIT)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.SET_UNITS_STATE)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.DIRECT_ATTACK)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.AERIAL_ATTACK)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.POUR_OIL)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.DIG_TUNNEL)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.BUILD_EQUIPMENT)!= null) {
                System.out.println();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.DISBAND_UNIT)!= null) {
                System.out.println();
            }
            else System.out.println("Invalid Command!");
        }
    }

    public Output enterChangeEnvironmentMenu() {
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu();
        changeEnvironmentMenu.run();
        return Output.ENTER_CHANGE_ENVIRONMENT_MENU;
    }
    public Output enterStoreMenu() {
        StoreMenu storeMenu = new StoreMenu();
        storeMenu.run();
        return Output.ENTER_STORE_MENU;
    }
    private Output enterTradeMenu() {
        TradeMenu tradeMenu = new TradeMenu();
        tradeMenu.run();
        return Output.ENTER_TRADE_MENU;
    }
    private Output enterGovernmentMenu() {
        GovernanceMenu governanceMenu = new GovernanceMenu();
        governanceMenu.run();
        return Output.ENTER_GOVERNANCE_MENU;
    }
    private String showMap(Matcher matcher) {return null;}

    private String dropBuilding(Matcher matcher) {
        return null;
    }

    private String selectBuilding(Matcher matcher) {
        return null;
    }

    private String createUnit(Matcher matcher) {
        return null;
    }

    private String selectUnit(Matcher matcher) {
        return null;
    }

    private String moveUnit(Matcher matcher) {
        return null;
    }

    private String patrolUnit(Matcher matcher) {
        return null;
    }

    private String setUnitState(Matcher matcher) {
        return null;
    }

    private String attack(Matcher matcher) {
        return null;
    }

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
