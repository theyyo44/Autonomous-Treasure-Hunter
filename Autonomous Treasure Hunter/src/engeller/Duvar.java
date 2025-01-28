package engeller;

import main.GamePanel;
import main.harita;

import javax.imageio.ImageIO;
import java.awt.*;

public class Duvar extends dinamikEngel{

    public Duvar(){
        name = "Duvar";
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/tilepng/wall.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/tilepng/winter_wall.png"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX;
        int screenY;

        if(worldX + gp.tileSize > gp.player.worldx - gp.player.screenx &&
                worldX - gp.tileSize < gp.player.worldx + gp.player.screenx &&
                worldY + gp.tileSize > gp.player.worldy - gp.player.screeny &&
                worldY - gp.tileSize < gp.player.worldy + gp.player.screeny)   {


            for (int i = 35; i < 50; i++) {
                screenX = gp.engel[i].worldX - gp.player.worldx + gp.player.screenx;
                screenY = gp.engel[i].worldY - gp.player.worldy + gp.player.screeny;


                if (gp.engel[i].worldX/gp.tileSize<= harita.HaritaBoyutu/2){

                    g2.drawImage(image1, screenX, screenY, gp.tileSize*1, gp.tileSize*10,null);



                }else{
                    g2.drawImage(image2, screenX, screenY, gp.tileSize*1, gp.tileSize*10,null);

                }

            }





        }
    }
}
