package controller;

import enums.Output;
import model.Cell;
import model.Game;
import model.units.Assassin;
import model.units.Unit;

public class MapController {

    private final Game game;

    public MapController(Game game) {
        this.game = game;
    }

    public String showMap(int row, int column) {
        if (!(row >= 1 && row <= game.getCells().length && column >= 1 && column <= game.getCells()[0].length))
            return Output.WRONG_COORDINATES.toString();
        game.setCurrentMapX(row--);
        game.setGetCurrentMapY(column--);
        StringBuilder output = new StringBuilder();
        output.append("-".repeat(11 * 6));
        output.append("\n");
        for (int h = 0; h < 11; h++) {
            for (int k = 0; k < 3; k++) {
                output.append("|");
                for (int i = 0; i < 11; i++) {
                    for (int j = 0; j < 5; j++) {
                        if ((row - 6 + h) >= 0 && (column - 6 + i) >= 0 && (column - 6 + i) <= game.getCells()[0].length && (row - 6 + h) <= game.getCells().length) {
                            output.append(game.getCells()[row - 6 + h][column - 6 + i].getTexture().getColor());
                            if (game.getCells()[i][j].getBuilding() == null || game.getCells()[i][j].getBuilding().getName().equals("killing pit"))
                                output.append("#").append("\u001B[0m");
                            else
                                output.append("b").append("\u001B[0m");
                        } else output.append(" empty ");
                    }
                    output.append("|");
                }
                output.append("\n");
            }
        }
        output.append("-".repeat(11 * 6));
        return output.toString();
    }

    public String showMapDetails(int row, int column) {
        Cell cell = game.getCells()[row - 1][column - 1];
        StringBuilder details = new StringBuilder();
        details.append("Texture : ").append(cell.getTexture().toString());
        if (cell.HasRock()) details.append("\nRocky");
        if (cell.getTreeType() != null) details.append("\nTree : ").append(cell.getTreeType().toString());
        if (cell.getBuilding() != null && (cell.getBuilding().getOwner().equals(game.getCurrentPlayer()) || !cell.getBuilding().getName().equals("killing pit"))) {
            details.append("\n").append("Buildings : ").append(cell.getBuilding().getName());
            details.append(" | owner : ").append(cell.getBuilding().getOwner().getUsername());
            details.append(" | hit-point : ").append(cell.getBuilding().getHp());
        }
        if (cell.getUnits().size() > 0) {
            details.append("\nUnits : ").append(cell.getUnits().size()).append(" units");
            for (Unit unit : cell.getUnits()) {
                if (unit instanceof Assassin && (!((Assassin) unit).isHidden() || unit.getOwner().getUsername().equals(game.getCurrentPlayer().getUsername())))
                    details.append("\n").append(unit.getName());
                details.append(" | hit point ").append(unit.getHitPoint());
            }
        }
        return details.toString();
    }

    public String moveMap(int horizontalDisplacement, int verticalDisplacement) {
        return showMap(game.getCurrentMapX() + verticalDisplacement, game.getGetCurrentMapY() + horizontalDisplacement);
    }
}
