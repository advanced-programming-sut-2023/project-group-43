package model.units;

import enums.unitEnums.UnitsEnum;
import model.*;
import model.buildings.Building;

public class Engineer extends Unit{
    public Engineer(User owner, String name) {
        super(owner, name);
        hasOil = false;
    }
    private boolean hasOil;

    public void chargeTar(){
        if(!hasOil) {
            Building building = this.getOwner().getGovernance().getBuildingByName("oil smelter");
            if (building == null) return;
            setCurrentTargetX(building.getCell().getX());
            setCurrentTargetY(building.getCell().getY());
            if (this.getCell().getX() == this.getCurrentTargetX() && this.getCell().getY() == getCurrentTargetY()) {
                hasOil = true;
                setCurrentTargetY(-1);
                setCurrentTargetX(-1);
            }
        }
    }

    public void pourOil(Game game, String direction) {
        hasOil = false;
        int x = this.getCell().getX();
        int y = this.getCell().getY();
        Cell[][] cells = game.getCells();
        Cell target = null;
        switch (direction) {
            case "up":
                if (y > 0) target = cells[x][y - 1];
                break;
            case "down":
                if (y <= game.getColumn() - 2) target = cells[x][y + 1];
                break;
            case "left":
                if (x > 0) target = cells[x - 1][y];
                break;
            case "right":
                if (x <= game.getRow() - 2) target = cells[x + 1][y];
        }
        if (target != null) {
            for (Unit unit: target.getUnits()) {
                if (!unit.getOwner().getUsername().equals(getOwner().getUsername()))
                    unit.setHitPoint(0);
            }
        }
    }
}
