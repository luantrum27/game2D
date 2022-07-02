package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.*;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gq;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gq) {
        this.gq = gq;

        tile = new Tile[10];
        mapTileNum = new int[gq.maxWorldCol][gq.maxWorldRow];

        getTileImage();
        loadMap("../res/maps/mapV1.txt");
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/stone.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/water.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/tree.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("../res/tiles/sand.png"));

        } catch (IOException e) {
            // TODO: handle exception
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gq.maxWorldCol && row < gq.maxWorldRow) {
                String line = br.readLine();

                while (col < gq.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gq.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gq.maxWorldCol && worldRow < gq.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gq.tileSize;
            int worldY = worldRow * gq.tileSize;
            int screenX = worldX - gq.player.worldX + gq.player.screenX;
            int screenY = worldY - gq.player.worldY + gq.player.screenY;
            if (worldX + gq.tileSize > gq.player.worldX - gq.player.screenX &&
                    worldX - gq.tileSize < gq.player.worldX + gq.player.screenX &&
                    worldY + gq.tileSize > gq.player.worldY - gq.player.screenY &&
                    worldY - gq.tileSize < gq.player.worldY + gq.player.screenY) {

                if (tileNum == 4) {

                    g2.drawImage(tile[0].image, screenX, screenY, gq.tileSize, gq.tileSize,
                            null);

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gq.tileSize, gq.tileSize,
                            null);
                } else {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, gq.tileSize, gq.tileSize, null);

                }
            }
            worldCol++;
            if (worldCol == gq.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}