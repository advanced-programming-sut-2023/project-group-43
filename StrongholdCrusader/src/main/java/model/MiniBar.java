package model;

import enums.ImageEnum;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import view.ProfileMenu;

public class MiniBar {
    public ScrollBar scrollBar;
    public Pane pane = new Pane();
    public AnchorPane leftAnchorPane = new AnchorPane();
    public String selectedBuildingName;
    public ImageView[] allBuildingImages = new ImageView[41];

    public MiniBar() {
        this.pane.setMinSize(1600, 200);
        this.leftAnchorPane.setMinSize(1000, 400);
        leftAnchorPane.setBackground(new Background(new BackgroundImage(new Image(ProfileMenu.class.getResource("/images/background/oldPaper.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));
        leftAnchorPane.setLayoutY(600);
        setAllBuildingImages();
        addingBuildingImagesOnLeftAnchorPane();
        //leftAnchorPane.getChildren().add(scrollBar);
        pane.getChildren().add(leftAnchorPane);
    }
    public void setAllBuildingImages() {
        allBuildingImages[0] = new ImageView(ImageEnum.APPLE_GARDEN.getImage());
        allBuildingImages[1] = new ImageView(ImageEnum.ARMOURY.getImage());
        allBuildingImages[2] = new ImageView(ImageEnum.BAKERY.getImage());
        allBuildingImages[3] = new ImageView(ImageEnum.BARRACK.getImage());
        allBuildingImages[4] = new ImageView(ImageEnum.BEER_BREWING.getImage());
        allBuildingImages[5] = new ImageView(ImageEnum.BIG_STONE_GATEHOUSE.getImage());
        allBuildingImages[6] = new ImageView(ImageEnum.BLACK_SMITH.getImage());
        allBuildingImages[7] = new ImageView(ImageEnum.CHURCH.getImage());
        allBuildingImages[8] = new ImageView(ImageEnum.CIRCLE_TOWER.getImage());
        allBuildingImages[9] = new ImageView(ImageEnum.DAIRY_PRODUCT.getImage());
        allBuildingImages[10] = new ImageView(ImageEnum.DEFENSIVE_TOWER.getImage());
        allBuildingImages[11] = new ImageView(ImageEnum.CAGED_WAR_DOGS.getImage());
        allBuildingImages[12] = new ImageView(ImageEnum.DRAW_BRIDGE.getImage());
        allBuildingImages[13] = new ImageView(ImageEnum.ENGINEER_GUILD.getImage());
        allBuildingImages[14] = new ImageView(ImageEnum.FLETCHER.getImage());
        allBuildingImages[15] = new ImageView(ImageEnum.HEADQUARTER.getImage());
        allBuildingImages[16] = new ImageView(ImageEnum.HOP_FARM.getImage());
        allBuildingImages[17] = new ImageView(ImageEnum.HOVEL.getImage());
        allBuildingImages[18] = new ImageView(ImageEnum.HUNTING_POST.getImage());
        allBuildingImages[19] = new ImageView(ImageEnum.INN.getImage());
        allBuildingImages[20] = new ImageView(ImageEnum.LOOKOUT_TOWER.getImage());
        allBuildingImages[21] = new ImageView(ImageEnum.MARKET.getImage());
        allBuildingImages[22] = new ImageView(ImageEnum.MERCENARY_POST.getImage());
        allBuildingImages[23] = new ImageView(ImageEnum.MILL.getImage());
        allBuildingImages[24] = new ImageView(ImageEnum.OIL_SMELTER.getImage());
        allBuildingImages[25] = new ImageView(ImageEnum.OX_TETHER.getImage());
        allBuildingImages[26] = new ImageView(ImageEnum.PERIMETER_TOWER.getImage());
        allBuildingImages[27] = new ImageView(ImageEnum.PITCH_DITCH.getImage());
        allBuildingImages[28] = new ImageView(ImageEnum.PITCH_RIG.getImage());
        allBuildingImages[29] = new ImageView(ImageEnum.POLETURNETR.getImage());
        allBuildingImages[30] = new ImageView(ImageEnum.QUARRY.getImage());
        allBuildingImages[31] = new ImageView(ImageEnum.SIEGE_TENT.getImage());
        allBuildingImages[32] = new ImageView(ImageEnum.SMALL_STONE_GATEHOUSE.getImage());
        allBuildingImages[33] = new ImageView(ImageEnum.SQUARE_TOWER.getImage());
        allBuildingImages[34] = new ImageView(ImageEnum.STABLE.getImage());
        allBuildingImages[35] = new ImageView(ImageEnum.STOCKPILE.getImage());
        allBuildingImages[36] = new ImageView(ImageEnum.TREE.getImage());
        allBuildingImages[37] = new ImageView(ImageEnum.WHEAT_FARM.getImage());
        allBuildingImages[38] = new ImageView(ImageEnum.WOOD_CUTTER.getImage());
        allBuildingImages[39] = new ImageView(ImageEnum.SAMPLE_MINI_MAP.getImage());//
    }
    public void addingBuildingImagesOnLeftAnchorPane() {
        for (int i = 0; i <= 38; i++) {
            allBuildingImages[i].setFitHeight(40);
            allBuildingImages[i].setFitWidth(20);
            allBuildingImages[i].setX(i * 20);
            allBuildingImages[i].setY(50);
            leftAnchorPane.getChildren().add(allBuildingImages[i]);
        }
        allBuildingImages[39].setX(800);
        allBuildingImages[39].setY(0);
        allBuildingImages[39].setFitHeight(90);
        allBuildingImages[39].setFitWidth(100);
        leftAnchorPane.getChildren().add(allBuildingImages[39]);
        /*imageView.setOnMouseClicked(mouseEvent -> {});
        Button appleFarm = new Button("appleFarm");
        Group root = new Group(imageView);
        leftAnchorPane.getChildren().add(root);*/
    }
    public void addListenerToFindTheSelectedBuilding() {
        for (int i = 0; i <= 38; i++) {
            String name = ImageEnum.getNameByImage(allBuildingImages[i].getImage());
            allBuildingImages[i].setOnMouseClicked(mouseEvent -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(name + " is selected\nnow you should choose a cell to place " + name + " on it");
                alert.show();
                selectedBuildingName = name;
            });
        }
    }
    /*private class ButtonClickListener implements ActionListener{
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
    }*/
}
