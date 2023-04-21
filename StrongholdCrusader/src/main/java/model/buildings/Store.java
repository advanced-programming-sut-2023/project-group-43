package model.buildings;

import enums.BuildingEnums.BuildingStructure;
import model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class Store extends Building{

    //look after using Integer instead of int during the game
    HashMap<String , Integer>stockpile = new HashMap<String, Integer>();
    HashMap<String , Integer>foodStockpile = new HashMap<String , Integer>();
    HashMap<String , Integer>armoury = new HashMap<String , Integer>();
    public Store(String name, User owner, BuildingStructure buildingStructure) {
        super(name, owner, buildingStructure);
    }
}
