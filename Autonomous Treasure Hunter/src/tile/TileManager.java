package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNumber[][];


    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNumber = new int [gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/map/map.txt");
    }
    public void  getTileImage() {
        try {

            tile[0]= new Tile();
            tile[0].image= ImageIO.read(getClass().getResourceAsStream("/tilepngs/yer.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tilepngs/tehkikeli_yer.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tilepngs/yer.png"));
            tile[2].collision =true;

            tile[3]= new Tile();
            tile[3].image= ImageIO.read(getClass().getResourceAsStream("/tilepngs/yeşil.png"));

            tile[4]= new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tilepngs/wall.png"));
            tile[4].collision =true;

            tile[5]= new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tilepngs/winter_wall.png"));
            tile[5].collision =true;

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  void loadMap(String filePath){
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;

            while (col< gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNumber[col][row] = num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col = 0;
                    row++;
                }


            }


            br.close();
        }catch (Exception e){

        }
    }

    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;


        while (worldcol< gp.maxWorldCol && worldrow< gp.maxWorldRow){

            int tileNum = mapTileNumber[worldcol][worldrow];
            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;
            int screenX = worldX - gp.player.worldx + gp.player.screenx;
            int screenY = worldY - gp.player.worldy + gp.player.screeny;

            if( worldX + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldX - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldY + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldY - gp.tileSize < gp.player.worldy + gp.player.screeny)   {
                if(tileNum == 0){
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
                }else{
                    g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
                }


            }


            worldcol++;

            if(worldcol == gp.maxWorldCol){
                worldcol = 0;
                worldrow++;

            }

        }
    }

}

