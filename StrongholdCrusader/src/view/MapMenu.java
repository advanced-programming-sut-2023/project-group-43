package view;

import controller.GameController;
import controller.MapController;
import model.DataBase;

import java.util.regex.Matcher;

public class MapMenu extends Menu{

    private MapController mapController;

    public MapMenu(MapController mapController) {
        this.mapController = mapController;
    }

    public void run(){
        System.out.println("map menu:");
    }

    private String moveMap(Matcher matcher){return null;}

    private String showDetails(Matcher matcher){return null;}
}
