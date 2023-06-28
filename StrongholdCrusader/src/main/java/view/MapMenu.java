package view;

import controller.GameControllers.MapController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MapMenu extends Application {

    private MapController mapController;

    private Stage stage;

    private Scene scene;

    private AnchorPane root = new AnchorPane();

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
    }


}
