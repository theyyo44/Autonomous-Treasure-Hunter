package engeller;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class hareketliEngel extends engeller{

    BufferedImage image1, image2, image3, image4;
    public  int speed;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber =1;
    public  boolean collisinOn = false;
    public int haraketCounter = 0;

    public hareketliEngel(GamePanel gp){
        this.gp = gp;
    }

    public void Hareket(){}

    public void update() {

        Hareket();

        collisinOn = false;




        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left":worldX -= speed; break;
            case "right":worldX += speed;break;

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

}
