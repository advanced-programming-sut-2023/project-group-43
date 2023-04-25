package model.buildings;

import model.User;

public class BuildingBuilder {

    public Building BuildingBuilder(String name , User owner){
        //TODO : we should declare people capacity with details
        switch (name){
            case "small stone gatehouse":
                return new CastleDepartment(name, owner,100,8 );
            case "big stone gatehouse":
                    return new CastleDepartment(name,owner,100,10);
            case "draw bridge":
                    return new CastleDepartment(name,owner,100,0);
            case "look out tower":
                    return new CastleDepartment(name,owner,150,0);
            case "perimeter tower":
                return new CastleDepartment(name,owner,153 , 0);
            case "defensive turret":
            case "square tower":
            case "circle tower":
            case "siege tent":
            case "caged war dogs":
            case "pitch ditch":
            case "oil smelter":
            case "wheat farm":
            case "hop farm":
            case "hunting post":
            case "apple garden":
            case "stable":
            case "howel":
            case "wood cutter":
            case "pitch rig":
            case "quarry":
            case "iron mine":
            case "":



        }
    }
}
