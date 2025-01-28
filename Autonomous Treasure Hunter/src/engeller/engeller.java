package engeller;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class engeller {

    GamePanel gp;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(5,0,24,24);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;



    public void draw(Graphics2D g2, GamePanel gp){}
}
