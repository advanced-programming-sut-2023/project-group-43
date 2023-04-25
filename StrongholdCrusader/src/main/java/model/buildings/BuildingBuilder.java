package model.buildings;

import model.User;

public class BuildingBuilder {

    public Building BuildingBuilder(String name , User owner){
        switch (name){
            case "small stone gatehouse":
                return new CastleDepartment(name, owner,100,8 );
            case "big stone gatehouse":
                    return new CastleDepartment(name,owner,100,10);
            case "draw bridge":
                    return new CastleDepartment(name,owner,100,0);
            case "look out tower":
                    return new CastleDepartment(name,owner,100,)
        }
    }
}
