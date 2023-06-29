package model;

import enums.ImageEnum;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.buildings.Building;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class miniMap {
    public Stage stage;
    public Scene scene;
    public ScrollBar scrollBar;
    public Pane pane = new Pane();
    public AnchorPane leftAnchorPane = new AnchorPane();
    public Building selectedBuilding;

    public miniMap() {
        this.pane.setMinSize(1600, 200);
        this.leftAnchorPane.setMinSize(1000, 200);
        scrollBar = new ScrollBar();
        scrollBar.setMinSize(1000,200);
        leftAnchorPane.getChildren().add(scrollBar);
        pane.getChildren().add(leftAnchorPane);
    }
    public void addingBuildingImagesOnLeftAnchorPane() {
        ImageView imageView = new ImageView(ImageEnum.APPLE_GARDEN.getImage());
        imageView.setX(50);
        imageView.setY(25);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        Button appleFarm = new Button("appleFarm");
        appleFarm.setActionCommand("appleFarm");
        appleFarm.addActionListener(new ButtonClickListener());
        Group root = new Group(imageView);
        leftAnchorPane.getChildren().add(root);
    }
    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if( command.equals( "OK" ))  {
                //statusLabel.setText("Ok Button clicked.");
            }
            else if( command.equals( "Submit" ) )  {
                //statusLabel.setText("Submit Button clicked.");
            }
            else  {
                //statusLabel.setText("Cancel Button clicked.");
            }
        }
    }
}
