package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public abstract class entity {

    GamePanel gp;
    public  int worldx,worldy;
    public  int speed;

    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber =1;

    public Rectangle solidArea = new Rectangle(0,0, 24,24);
    public int solidAreaDefaultX, solidAreaDefaultY;

    public  boolean collisinOn = false;
    public int haraketCounter = 0;

    public boolean onPath = true;
    public void Hareket(){}

    public void update() {

        Hareket();

        collisinOn = false;
        gp.cKontrol.kontrolZemin(this);


        if(collisinOn == false||  collisinOn == true){
            switch (direction){
                case "up": worldy -= speed; break;
                case "down": worldy += speed; break;
                case "left":worldx -= speed; break;
                case "right":worldx += speed;break;

            }
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNumber==1){
                spriteNumber=2;
            }
            else if (spriteNumber==2) {
                spriteNumber=1;
            }
            else if(spriteNumber== 3){
                spriteNumber = 2;
            }
            spriteCounter = 0;
        }

    }

    public void draw(Graphics2D g2){ }
    public entity(GamePanel gp){

        this.gp=gp;
    }

}
