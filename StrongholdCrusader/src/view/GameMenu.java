package view;

import controller.*;
import enums.Output;
import enums.menuEnums.GameMenuCommands;
import enums.menuEnums.GovernanceMenuCommands;
import model.DataBase;

import javax.print.DocFlavor;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{

    private GameController gameController;
    private ChangeEnvironmentController changeEnvironmentController;
    private GovernanceController governanceController;
    private TradeController tradeController;
    private StoreController storeController;

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
                enterChangeEnvironmentMenu();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_STORE_MENU)!= null) {
                enterStoreMenu();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_TRADE_MENU)!= null) {
                enterTradeMenu();
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_GOVERNANCE_MENU)!= null) {
                enterGovernmentMenu();
            }
            //game
            if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.SELECT_BUILDING))!= null) {
                System.out.println(selectBuilding(matcher));
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.CREATE_UNIT))!= null) {
                System.out.println(createUnit(matcher));
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.REPAIR_CASTLE)!= null) {
                System.out.println(gameController.repairCastle());
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
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.ATTACK))!= null) {
                System.out.println(attack(matcher));
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
            else if ((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.PATROL_UNIT)) != null) {
                System.out.println(patrolUnit(matcher));
            }
            else System.out.println("Invalid Command!");
        }
    }

    public void enterChangeEnvironmentMenu() {
        ChangeEnvironmentMenu changeEnvironmentMenu = new ChangeEnvironmentMenu(changeEnvironmentController);
        changeEnvironmentMenu.run();
    }
    public void enterStoreMenu() {
        StoreMenu storeMenu = new StoreMenu(storeController);
        storeMenu.run();
    }
    private void enterTradeMenu() {
        TradeMenu tradeMenu = new TradeMenu(tradeController);
        tradeMenu.run();
    }
    private void enterGovernmentMenu() {
        GovernanceMenu governanceMenu = new GovernanceMenu(governanceController);
        governanceMenu.run();
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
        return gameController.disbandUnit();
    }

    private String setUnitState(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String state = matcher.group("state");
        return gameController.setUnitState(x,y,state);
    }

    private String attack(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        String item = matcher.group("item");
        return gameController.attack(x , y , item);
    }
    private String pourOil(Matcher matcher) {
        String direction = matcher.group("direction");
        return gameController.pourOil(direction);
    }
    private String digTunnel(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("x"));
        int y = Integer.parseInt(matcher.group("y"));
        return gameController.digTunnel(x,y);
    }
    private String buildEquipment(Matcher matcher) {
        String equipmentName = matcher.group("equipmentName");
        return gameController.buildEquipment(equipmentName);
    }


}
