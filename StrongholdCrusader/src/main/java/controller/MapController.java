package controller;
import model.Camera;
import model.Cell;
import model.Game;

import java.awt.*;

public class MapController {

    private Game game;
    private Camera camera = new Camera();

    public MapController(Game game) {
        this.game = game;
    }

    public void showMap(int row, int column) {
        for (column = 0; column < game.getCells()[0].length; column++) {
            for (row = 0; row < game.getCells().length; row++) {
                Cell cell = game.getCells()[row][column];
                int x = column * cell.getCellSize();
                int y = row * cell.getCellSize();
                drawTile(cell, y, x);
            }
        }
        //game.getCells().drawImage(atlasImage, 192, 0, 64, 64, 128, 320, 64, 64);
    }
    public void drawImage(Image image, int a, int b, int c, int d, int row, int column, int e, int f) {}
    public void drawTile(Cell cell, int row, int column) {}
    public void worldToScreen(int x, int y) {
        //return { x: x - camera.getRow(), y: y - camera.getColumn() };
    }

    public void screenToWorld(int x, int y) {
        //return { x: x + camera.getRow(), y: y + camera.getColumn() };
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
