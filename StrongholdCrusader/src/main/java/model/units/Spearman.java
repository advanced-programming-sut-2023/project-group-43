package model.units;

import enums.unitEnums.TroopType;
import enums.unitEnums.UnitsEnum;
import model.Cell;
import model.Game;
import model.User;
import model.buildings.Building;

public class Spearman extends Unarmed{

    public Spearman(User owner, String name, TroopType troopType) {
        super(owner, name, troopType);
    }

    public void dropLadder(Game game) {
        Cell[][] cells = game.getCells();
        int sx = getCell().getX() - 1;
        int sy = getCell().getY() - 1;
        if (sx < 0) sx = 0;
        if (sy < 0) sy = 0;
        int fx = getCell().getX() + 1;
        int fy = getCell().getY() + 1;
        if (fx >= game.getRow()) fx--;
        if (fy >= game.getColumn()) fy--;
        for (int x = sx; x <= fx; x++) {
            for (int y = sy; y <= fy;y++) {
                Building building;
                if ((building = cells[x][y].getBuilding()) != null) {
                    if (building.getOwner().getUsername().equals(getOwner().getUsername())) {
                        building.setLadder(false);
                    }
                }
            }
        }
    }
}
