package view;

import controller.GameControllers.MapController;
import enums.ImageEnum;
import enums.environmentEnums.Texture;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Cell;
import model.DataBase;
import model.Game;

public class MapMenu extends Application {

    private MapController mapController = new MapController(new Game());

    private Stage stage;

    private Scene scene;

    private AnchorPane root = new AnchorPane();
    private ScrollBar scrollBar = new ScrollBar();

    int x = -100;
    int y = -100;
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
        root.setMinSize(1500,600);
    }

    private void setScrollBar() {
        scrollBar.setMinSize(1000,700);
        scrollBar.setValue(10);
        scrollBar.setOrientation(Orientation.VERTICAL);
        scrollBar.setUnitIncrement(12);
        scrollBar.setBlockIncrement(10);
        root.getChildren().add(scrollBar);
    }

    private void setCells() {
//        for(int i = 0 ; i < mapController.getGame().getCells().length ; i++){
//            //System.out.println("make i cells" + i);
//            y += 20;
//            for(int j = 0 ; j < mapController.getGame().getCells().length ; j++){
//                //System.out.println("make j cells " + j);
//                x += 20;
//                GridPane cell = loadCell(mapController.getGame().getCells()[i][j]);
//                setCell(cell);
//            }
//        }
        for(int i = 0 ; i < mapController.getGame().getRow() ; i++){
            //System.out.println("make i cells" + i);
            x = -100;
            y += 100;
            for(int j = 0 ; j < mapController.getGame().getColumn() ; j++){
                //System.out.println("make j cells " + j);
                x += 100;
                GridPane cell = loadCell(mapController.getGame().getCells()[i][j]);
                setCell(cell);
            }
        }
    }

    private GridPane loadCell(Cell cell) {
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(100,100);

        Image texture = getTexture(cell);
        Image building = null;
        Image rock = null;

        if(cell.getBuilding() != null)
            building = getBuilding(cell);

        if(cell.HasRock())
            rock = getRock(cell);


        ImageView textureImageview = new ImageView(texture);
        ImageView item = new ImageView();

        textureImageview.setFitHeight(100);
        textureImageview.setFitWidth(100);
        textureImageview.setImage(texture);

        item.setFitWidth(80);
        item.setFitHeight(80);

        if(building != null)
            item.setImage(building);

        if(rock != null)
            item.setImage(rock);

        gridPane.getChildren().add(textureImageview);

        if(item.getImage() != null)
            gridPane.getChildren().add(item);


        return gridPane;
    }

    private Image getTexture(Cell cell){
        Image texture;
        texture = ImageEnum.getImageByName(cell.getTexture().getName());
        return texture;
    }

    private Image getBuilding(Cell cell){
        Image building;
        building = ImageEnum.getImageByName(cell.getBuilding().getName());
        return building;
    }

    private Image getTree(Cell cell){
        Image tree;
        tree = ImageEnum.TREE.getImage();
        return tree;
    }

    private Image getRock(Cell cell){
        Image rock;
        rock = ImageEnum.ROCK.getImage();
        return rock;
    }

    //ignore tunnel
    private void setCell(GridPane cell){
        cell.setLayoutX(x);
        cell.setLayoutY(y);
        root.getChildren().add(cell);
    }

}
