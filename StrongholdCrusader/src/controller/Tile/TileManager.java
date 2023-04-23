package controller.Tile;

import model.Cell;
import model.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    Game gp;
    Cell cell;
    Tile[] tile;

    public TileManager(Game gp) {
        this.gp = gp;
        tile = new Tile[16];
        getTileImage();
    }
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("filepath"));//file path is wrong!
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int tileSize = 5;//for example
        g2.drawImage(tile[0].image, 0, 0, tileSize, tileSize, null);
    }
}
