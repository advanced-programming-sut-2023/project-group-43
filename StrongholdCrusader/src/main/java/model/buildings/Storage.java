package model.buildings;

import enums.BuildingEnums.BuildingEnum;
import model.User;
import java.util.HashMap;

public class Storage extends Building{

    //look after using Integer instead of int during the game
    HashMap<String , Integer>stockpile = new HashMap<String, Integer>();
    HashMap<String , Integer>foodStockpile = new HashMap<String , Integer>();
    HashMap<String , Integer>armoury = new HashMap<String , Integer>();
    public Storage(String name, User owner) {
        super(name, owner);
    }

    //TODO
    //before starting game you should set items for stockpile based on material enum

    public int getAmountOfItemInStockpile(String item){
        return stockpile.get(item);
        //TODO : what about nulls?
        }
    }


