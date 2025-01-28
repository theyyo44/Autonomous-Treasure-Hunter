package engeller;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Kus extends hareketliEngel{
    public Kus(GamePanel gp) {
        super(gp);

        direction = "down";
        speed= 1;
        getImage();
    }
    public void getImage(){
        try {


            image1= ImageIO.read((getClass().getResourceAsStream("/kuş/left1.png")));
            image2= ImageIO.read((getClass().getResourceAsStream("/kuş/left2.png")));


            image3= ImageIO.read((getClass().getResourceAsStream("/kuş/right1.png")));
            image4= ImageIO.read((getClass().getResourceAsStream("/kuş/right2.png")));




        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2){

        BufferedImage image = null;
        int screenX = worldX - gp.player.worldx + gp.player.screenx;
        int screenY = worldY - gp.player.worldy + gp.player.screeny;

        if(worldX + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldX - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldY + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldY - gp.tileSize < gp.player.worldy + gp.player.screeny)   {
            switch (direction){

                case "down":
                    if(spriteNumber == 1){
                        image = image3;
                    }
                    if(spriteNumber == 2){
                        image = image4;
                    }
                    break;
                case "up":
                    if(spriteNumber == 1){
                        image = image1;
                    }
                    if(spriteNumber == 2){
                        image = image2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
            g2.setColor(Color.RED); // Kırmızı rengi seç


        }

    }

    @Override
    public void Hareket(){
        haraketCounter ++;
        if (haraketCounter == 100){


            if (direction.equals("down")){
                direction = "up";
            }else {
                direction = "down";
            }
            haraketCounter = 0;
        }

    }
}


