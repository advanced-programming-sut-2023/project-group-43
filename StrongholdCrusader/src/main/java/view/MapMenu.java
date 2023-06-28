package view;

import controller.GameControllers.MapController;
import enums.ImageEnum;
import enums.environmentEnums.Texture;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Cell;
import model.DataBase;

public class MapMenu extends Application {

    private MapController mapController;

    private Stage stage;

    private Scene scene;

    private AnchorPane root = new AnchorPane();

    int x;
    int y;
    public void setMapController(MapController mapController) {
        this.mapController = mapController;
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        initialize();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void initialize(){
        setRootPane();
        setScrollBar();
        setCells();
    }

    private void setRootPane() {
        root.setMinSize(1600,800);
    }

    private void setScrollBar() {
        ScrollBar scrollBar = new ScrollBar();
        scrollBar.setMinSize(1600,800);
        root.getChildren().add(scrollBar);
    }

    private void setCells() {
        for(int i = 0 ; i < mapController.getGame().getCells().length ; i++){
            for(int j = 0 ; j < mapController.getGame().getCells().length ; j++){
                GridPane cell = loadCell(mapController.getGame().getCells()[i][j]);
                setCell(cell);
            }
        }
    }

    private GridPane loadCell(Cell cell) {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(20,20);

        Image texture = getTexture(cell);
        Image Building = getBuilding(cell);
        Image tree = getTree(cell);
        Image rock = getRock(cell);

        return gridPane;
    }

    private Image getTexture(Cell cell){
        Image texture;
        switch (cell.getTexture()){
            case SEA -> ;
            case OIL ->;
            case IRON ->;
            case ROCK -> ;
            case BEACH -> ;
            case GRASS -> ;
            case PLAIN -> ;
            case DENSE_GRASSLAND -> ;
            case RIVER -> ;
            case GROUND -> ;
            case BIG_POND -> ;
            case SMALL_POND -> ;
            case SHALLOW_WATER -> ;
            case BOULDER -> ;
            case MEADOW ->;
            case GRAVEL_GROUND -> ;
            default -> texture = null;
        }
        return texture;
    }

    private Image getBuilding(Cell cell){
        Image building;

    }

    private Image getTree(Cell cell){}

    private Image getRock(Cell cell){}

    //ignore tunnel
    private void setCell(GridPane cell){
        root.getChildren().add(cell);
    }

}
