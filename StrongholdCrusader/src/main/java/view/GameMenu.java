package view;

import controller.*;
import enums.Output;
import enums.Validations;
import enums.menuEnums.GameMenuCommands;
import enums.unitEnums.UnitState;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu extends Menu{

    private GameController gameController;
    private MapController mapController;
    private GovernanceController governanceController;
    private TradeController tradeController;
    private StoreController storeController;

    private int turns, numberOfPlayers;

    private String x, y;

    public GameMenu(GameController gameController) {

        this.gameController = gameController;
        mapController = new MapController(gameController.getGame());
        storeController = new StoreController(gameController.getGame(), gameController);
        governanceController = new GovernanceController(gameController.getGame());
        tradeController = new TradeController(gameController.getGame());
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public void run() {
        System.out.println("game menu:");
        gameController.getGame().setCurrentPlayer(gameController.getGame().getCurrentUser());
        while (turns > 0){
            System.out.println("turn " + (gameController.getGame().getTurns() - turns + 1) + ":");
            System.out.println("turns left: "+ turns);
            for (int i = 0; i < numberOfPlayers; i++) {
                onePlayerTurn();
                gameController.goToNextPerson();
            }
            gameController.applyChanges();
            if(gameController.isGameEnded()){
                System.out.println("The End!");
                break;
            }
            turns--;
        }
        System.out.println(gameController.showGameResult());
    }

    public void enterMapMenu() {
        MapMenu mapMenu = new MapMenu(mapController);
        mapMenu.run();
    }
    public void enterStoreMenu(String name) {
        storeController.setStoreName(name);
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

    private Output selectBuilding(Matcher matcher) {
        if (parseMatcher(matcher))
        return gameController.selectBuilding(Integer.getInteger(x),Integer.parseInt(y));
        return null;
    }

    private Output createUnit(Matcher matcher) {
        String type = matcher.group("type");
        int count = Integer.parseInt(matcher.group("count"));
        return gameController.createUnit(type,count);
    }

    private Output selectUnit(Matcher matcher) {
        String type = Validations.getInfo("t", matcher.group());
        if (parseMatcher(matcher) && type != null)
            return gameController.selectUnit(Integer.getInteger(x),Integer.parseInt(y),type);
        return null;
    }

    private Output moveUnit(Matcher matcher) {
        if (parseMatcher(matcher))
            return gameController.moveUnit(Integer.getInteger(x),Integer.parseInt(y));
        return null;
    }

    private Output patrolUnit(Matcher matcher) {
        String x1 = Validations.getInfo("x1", matcher.group());
        String x2 = Validations.getInfo("x2", matcher.group());
        String y1 = Validations.getInfo("y1", matcher.group());
        String y2 = Validations.getInfo("y2", matcher.group());
        if (x1 == null || x2 == null || y1 == null || y2 == null) return null;
        if (x1.matches("\\d+") && x2.matches("\\d+") && y1.matches("\\d+") && y2.matches("\\d+"))
            return gameController.patrolUnit(Integer.getInteger(x1),Integer.parseInt(y1), Integer.parseInt(x2), Integer.parseInt(y2));
        return null;
    }

    private Output disbandUnit(Matcher matcher){
        return gameController.disbandUnit();
    }

    private Output setUnitState(Matcher matcher) {
        String state = Validations.getInfo("s", matcher.group());
        if (parseMatcher(matcher) && state != null)
        return gameController.setUnitState(Integer.parseInt(x),Integer.parseInt(y), state);
        return null;
    }

    private Output attack(Matcher matcher) {
        if (parseMatcher(matcher))
        return gameController.attack(Integer.parseInt(x),Integer.parseInt(y) , null);
        return null;
    }
    private Output attackEnemy(Matcher matcher) {
        if (parseMatcher(matcher))
        return gameController.attack(Integer.parseInt(x),Integer.parseInt(y) , "e");
        return null;
    }
    private Output pourOil(Matcher matcher) {
        String direction = matcher.group("direction");
        return gameController.pourOil(direction);
    }
    private Output digTunnel(Matcher matcher) {
        if(parseMatcher(matcher))
        return gameController.digTunnel(Integer.parseInt(x),Integer.parseInt(y));
        return null;
    }
    private Output buildEquipment(Matcher matcher) {
        String equipmentName = matcher.group("equipmentName");
        return gameController.buildEquipment(equipmentName);
    }

    private boolean parseMatcher(Matcher matcher) {
        x = Validations.getInfo("x", matcher.group());
        y = Validations.getInfo("y", matcher.group());
        if (x == null || y == null) return false;
        if (x.matches("\\d+") && y.matches("\\d+")) return true;
        return false;
    }

    private Output dropUnit(Matcher matcher)  {
        String x = Validations.getInfo("x", matcher.group());
        String y = Validations.getInfo("y", matcher.group());
        String type = Validations.getInfo("t", matcher.group());
        String count = Validations.getInfo("c", matcher.group());
        if (x != null && y != null && type != null && count != null) {
            if (x.matches("\\d+") && y.matches("\\d+") && count.matches("\\d+"))
                return gameController.dropUnit(Integer.parseInt(x), Integer.parseInt(y), type, Integer.parseInt(count));
        }
        return null;
    }

    private void onePlayerTurn() {
        System.out.println(gameController.getGame().getCurrentPlayer().getUsername() + " is playing");
        Scanner scanner = Menu.getScanner();
        String input = "";
        Output output;
        Matcher matcher;
        while (!input.equals("next person")) {
            output = null;
            //enter menu part
            if(gameController.getGame().getSelectedBuilding() != null &&
                    gameController.getGame().getSelectedBuilding().getName().matches("(market)|(barrack)|(engineer guild)|(mercenary post)")) {
                enterStoreMenu(gameController.getGame().getSelectedBuilding().getName());
                continue;
            }
            input = scanner.nextLine();
            if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_TRADE_MENU)!= null) {
                enterTradeMenu();
                continue;
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_MAP_MENU)!= null) {
                enterMapMenu();
                continue;
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.ENTER_GOVERNANCE_MENU)!= null) {
                enterGovernmentMenu();
                continue;
            }
            //game
            if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.SELECT_BUILDING))!= null) {
                output = selectBuilding(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.CREATE_UNIT))!= null) {
                output = createUnit(matcher);
            }
            else if(GameMenuCommands.getMatcher(input,GameMenuCommands.REPAIR_CASTLE)!= null) {
                output = gameController.repairCastle();
            }
            else if((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.SELECT_UNIT))!= null) {
                output = selectUnit(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.MOVE_UNIT))!= null) {
                output = moveUnit(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.SET_UNITS_STATE))!= null) {
                output = setUnitState(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.ATTACK))!= null) {
                output = attack(matcher);
            }
            else if ((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.ATTACK_ENEMY))!= null) {
                output = attackEnemy(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.POUR_OIL))!= null) {
                output = pourOil(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.DIG_TUNNEL))!= null) {
                output = digTunnel(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.BUILD_EQUIPMENT))!= null) {
                output = buildEquipment(matcher);
            }
            else if((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.DISBAND_UNIT))!= null) {
                output = disbandUnit(matcher);
            }
            else if ((matcher = GameMenuCommands.getMatcher(input,GameMenuCommands.PATROL_UNIT)) != null) {
                output = patrolUnit(matcher);
            } else if ((matcher = GameMenuCommands.getMatcher(input, GameMenuCommands.DROP_UNIT)) != null) {
                output = dropUnit(matcher);
            }
            if (output == null) System.out.println("Invalid Command!");
            else System.out.println(output.getString());
        }
    }


}
