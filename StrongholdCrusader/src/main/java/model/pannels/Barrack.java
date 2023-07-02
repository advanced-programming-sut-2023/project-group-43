package model.pannels;

import enums.ImageEnum;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class Barrack {
    private ScrollBar scrollBar;
    private Pane pane = new Pane();
    private ScrollPane scrollPane = new ScrollPane();
    private VBox vbox = new VBox();
    public ImageView[] allUnitsImages = new ImageView[11];

    public Barrack() {
        this.pane.setMinSize(200, 600);
        this.scrollPane.setMaxSize(200, 600);
        vbox.setMaxSize(200, 600);
        setAllUnitsImage();
        addingBuildingImagesOnLeftAnchorPane();
        pane.getChildren().add(scrollPane);
        setBackground(vbox);
    }

    private void setBackground(VBox pane) {
        pane.setBackground(new Background(new BackgroundImage(new Image(this.getClass().getResource("/images/background/oldPaper.png").toExternalForm()),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1, 1, true, true, false, false))));

    }
    public void setAllUnitsImage() {
        allUnitsImages[0] = new ImageView(ImageEnum.WORKER.getImage());
        allUnitsImages[1] = new ImageView(ImageEnum.ARCHER.getImage());
        allUnitsImages[2] = new ImageView(ImageEnum.CROSSBOWMAN.getImage());
        allUnitsImages[3] = new ImageView(ImageEnum.SWORDSMAN.getImage());
        allUnitsImages[4] = new ImageView(ImageEnum.BLACKMONK.getImage());
        allUnitsImages[5] = new ImageView(ImageEnum.SPEARMAN.getImage());
        allUnitsImages[6] = new ImageView(ImageEnum.PIKEMAN.getImage());
        allUnitsImages[7] = new ImageView(ImageEnum.MACEMAN.getImage());
        allUnitsImages[8] = new ImageView(ImageEnum.KNIGHT.getImage());
        allUnitsImages[9] = new ImageView(ImageEnum.TUNNLER.getImage());
        allUnitsImages[10] = new ImageView(ImageEnum.ASSASSIN.getImage());
    }
    public void addingBuildingImagesOnLeftAnchorPane() {
        for (int i = 0; i <= 10; i++) {
            allUnitsImages[i].setFitHeight(100);
            allUnitsImages[i].setFitWidth(100);
            allUnitsImages[i].setX(i * 100);
            allUnitsImages[i].setY(50);
            vbox.getChildren().add(allUnitsImages[i]);
        }
        scrollPane.setContent(vbox);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));
    }
}
