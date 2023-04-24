package controller;

import enums.Output;
import model.Cell;
import model.DataBase;
import model.Game;

public class MapController {

    private Game game;

    public MapController(Game game) {
        this.game = game;
    }

    public void showMap(int row, int column) {
        //TODO
    }
    public String showMapDetails(int row, int column) {
        Cell cell = game.getCells()[row - 1][column - 1];
        StringBuilder details = new StringBuilder();
        details.append("Texture : ").append(cell.getTexture().toString());
        if (cell.HasRock()) details.append("\nRocky");
        if (cell.getTreeType() != null) details.append("\nTree : ").append(cell.getTreeType().toString());
        if (cell.getBuilding() != null) {
            details.append("\n").append("Buldings : ").append(cell.getBuilding().getName());
            details.append(" | owner : ").append(cell.getBuilding().getOwner().getUsername());
            details.append(" | hitpoint : ").append(cell.getBuilding().getHp());
        }
        if (cell.getUnits().size() > 0) {
            details.append("\nUnits : ").append(cell.getUnits().size()).append(" units");
            for (int i = 0; i < cell.getUnits().size(); i++) {
                details.append("\n").append(cell.getUnits().get(i).getName());
                details.append(" | hit point ").append(cell.getUnits().get(i).getHitPoint());
            }
        }
        return details.toString();
    }
    public void moveMap(int horizontalDisplacement, int verticalDisplacement) {}

}
