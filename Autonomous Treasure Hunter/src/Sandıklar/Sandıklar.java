package Sandıklar;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.security.PublicKey;

public class Sandıklar {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,5,24,20);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.player.worldx + gp.player.screenx;
        int screenY = worldY - gp.player.worldy + gp.player.screeny;

        if(worldX + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldX - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldY + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldY - gp.tileSize < gp.player.worldy + gp.player.screeny)   {

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);

        }
    }
}
