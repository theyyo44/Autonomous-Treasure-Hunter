package entity;

import main.GamePanel;

import main.harita;

import javax.imageio.ImageIO;
import javax.print.DocFlavor;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class Player extends entity{

    Random rand = new Random();

    public static String ID ;
    public static String ad ;
    public final int screenx;
    public final int screeny;
    private int currentChestIndex = 0; // Şu anki sandık indeksi
    public int hasASandık = 0;
    public int hasGSandık = 0;
    public int hasZSandık = 0;
    public int hasBSandık = 0;
    public List<Point> geçilenYollar = new ArrayList<>();

    public  Player(GamePanel gp) {
        super(gp);




        screenx = gp.screenWidth/2 -(gp.tileSize/2);
        screeny = gp.screenHeight/2- (gp.tileSize/2);

        solidArea = new Rectangle();

        solidArea.x = 0;
        solidArea.y = 0;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = 20;
        solidArea.width = 20;

        setDefaultValues();
        getPlayerImage();

    }
    public String getID(){
        return  ID;
    }
    public void setID(String ID){
        this.ID=ID;
    }

    public String getAd(){
        return ad;
    }

    public void setAd(String ad){
        this.ad = ad;
    }

    public void setAdd(String ad){
        this.ad = ad;
    }
    public void setDefaultValues(){
        int x,y;

        do {
            x = rand.nextInt(harita.HaritaBoyutu);
            y = rand.nextInt(harita.HaritaBoyutu);
        } while (gp.tileM.mapTileNumber[x][y] == 1|| gp.tileM.mapTileNumber[x][y] == 2);
        worldx = gp.tileSize * x;
        worldy = gp.tileSize * y;
        speed = 2;
        direction = "down";

    }
    public void getPlayerImage(){
        try {
            up1= ImageIO.read((getClass().getResourceAsStream("/player/up1.png")));
            up2= ImageIO.read((getClass().getResourceAsStream("/player/up2.png")));
            up3= ImageIO.read((getClass().getResourceAsStream("/player/up3.png")));

            down1= ImageIO.read((getClass().getResourceAsStream("/player/down1.png")));
            down2= ImageIO.read((getClass().getResourceAsStream("/player/down2.png")));
            down3= ImageIO.read((getClass().getResourceAsStream("/player/down3.png")));

            left1= ImageIO.read((getClass().getResourceAsStream("/player/left1.png")));
            left2= ImageIO.read((getClass().getResourceAsStream("/player/left2.png")));
            left3= ImageIO.read((getClass().getResourceAsStream("/player/left3.png")));

            right1= ImageIO.read((getClass().getResourceAsStream("/player/right1.png")));
            right2= ImageIO.read((getClass().getResourceAsStream("/player/right2.png")));
            right3= ImageIO.read((getClass().getResourceAsStream("/player/right3.png")));



        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        if(onPath == true ){

            // En yakın sandığın indeksi ve mesafesi
            int closestIndex = -1;
            double closestDistance = Double.MAX_VALUE;

            for (int i = 0; i < gp.sandık.length; i++) {
                if (gp.sandık[i] != null) {
                    double distance = Math.sqrt(Math.pow(gp.sandık[i].worldX - worldx, 2) + Math.pow(gp.sandık[i].worldY - worldy, 2));
                    if (distance < closestDistance) {
                        closestIndex = i;
                        closestDistance = distance;
                    }
                }
            }

            if (closestIndex != -1) {
                int goalCol = gp.sandık[closestIndex].worldX / gp.tileSize;
                int goalRow = gp.sandık[closestIndex].worldY / gp.tileSize;
                searchPath(goalCol, goalRow); // En yakın sandığa doğru yol bul
            }



        }


        //Zemin Çarpma Kontrolü
        collisinOn = false;
        gp.cKontrol.kontrolZemin(this);

        //Sandık Çarpma Kontrol
        int sandıkindeks = gp.cKontrol.KontrolSandık(this,true);
        SandıkToplama(sandıkindeks);


        //Eğer çarpışma false ise player hareketedebilir
        if(collisinOn == false){
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
                spriteNumber=3;
            }
            else if(spriteNumber== 3){
                spriteNumber = 1;
            }
            spriteCounter = 0;
        }
        Point geçilenKoordinat = new Point(worldx / gp.tileSize, worldy / gp.tileSize);
        if (!geçilenYollar.contains(geçilenKoordinat)) {

            geçilenYollar.add(geçilenKoordinat);
            gp.tileM.mapTileNumber[geçilenKoordinat.x][geçilenKoordinat.y] = 3; // Tile'ı 3 yap
        }

    }
    public void SandıkToplama(int i) {

        if (i != 999){
            String SandıkTur = gp.sandık[i].name;

            switch (SandıkTur){
                case "Altın Sandık":
                    hasASandık++;
                    gp.ui.mesajgoster("Altın Sandık Bulundu(" + gp.sandık[i].worldX/gp.tileSize + ","+ gp.sandık[i].worldY/gp.tileSize + ")\n");
                    gp.sandık[i] = null;

                    break;
                case "Gümüş Sandık":
                    hasGSandık++;
                    gp.ui.mesajgoster("Gümüş Sandık Bulundu(" + gp.sandık[i].worldX/gp.tileSize + ","+ gp.sandık[i].worldY/gp.tileSize + ")\n");
                    gp.sandık[i] = null;
                    break;
                case "Zümrüt Sandık":
                    hasZSandık++;
                    gp.ui.mesajgoster("Zümrüt Sandık Bulundu(" + gp.sandık[i].worldX/gp.tileSize + ","+ gp.sandık[i].worldY/gp.tileSize + ")\n");
                    gp.sandık[i] = null;
                    break;
                case "Bronze Sandık":
                    hasBSandık++;
                    gp.ui.mesajgoster("Bronz Sandık Bulundu(" + gp.sandık[i].worldX/gp.tileSize + ","+ gp.sandık[i].worldY/gp.tileSize + ")");
                    gp.sandık[i] = null;
                    break;
            }
            if(hasBSandık == 5 && hasASandık == 5 && hasGSandık == 5 && hasZSandık == 5){
                gp.gameOver = true;
                gp.ui.oyunBitti = true;


            }
        }
    }
    public  void  draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNumber == 1){
                    image = up1;
                }
                if(spriteNumber == 2){
                    image = up2;
                }
                if(spriteNumber==3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                    image = down1;
                }
                if(spriteNumber == 2){
                    image = down2;
                }
                if(spriteNumber==3){
                    image = down3;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right1;
                }
                if(spriteNumber == 2){
                    image = right2;
                }
                if(spriteNumber==3){
                    image = right3;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left1;
                }
                if(spriteNumber == 2){
                    image = left2;
                }
                if(spriteNumber==3){
                    image = left3;
                }
                break;
        }

        g2.drawImage(image, screenx, screeny, gp.tileSize, gp.tileSize,null);

    }
    public  void searchPath(int goalCol, int goalRow) {

        int startCol= (worldx + solidArea.x)/gp.tileSize;
        int startRow = (worldy + solidArea.y)/gp.tileSize;

        gp.yolBulma.setNode(startCol,startRow,goalCol,goalRow,this);
        if (gp.yolBulma.search() == true){

            //Node worldx & worldy
            int nextX = gp.yolBulma.pathList.get(0).col * gp.tileSize;
            int nextY = gp.yolBulma.pathList.get(0).row * gp.tileSize;

            int LeftX = worldx+ solidArea.x;
            int RightX =  worldx+ solidArea.x+solidArea.width;
            int TopY =  worldy+ solidArea.y;
            int AltY = worldy +solidArea.y + solidArea.height;
            System.out.println(LeftX+","+RightX+","+TopY+","+AltY);
            if(TopY> nextY && LeftX >= nextX && RightX< nextX+gp.tileSize){
                System.out.println("deneme1");
                direction = "up";
            } else if (TopY < nextY && LeftX>= nextX && RightX< nextX + gp.tileSize) {
                System.out.println("deneme2");
                direction = "down";
            } else if (TopY>= nextY && AltY< nextY +gp.tileSize) {
                //left or right
                System.out.println("deneme3");
                if (LeftX> nextX){
                    direction = "left";
                    System.out.println("deneme4");
                }
                if(LeftX< nextX){
                    System.out.println("deneme5");
                    direction ="right";
                }
            }else if (TopY> nextY && LeftX > nextX){
                //up or left
                System.out.println("deneme6");
                direction= "up";
                gp.cKontrol.kontrolZemin(this);
                if(collisinOn == true){
                    System.out.println("deneme7");
                    direction= "left";
                }

            } else if (TopY> nextY && LeftX< nextX) {
                // up or right
                direction="up";
                System.out.println("deneme8");
                gp.cKontrol.kontrolZemin(this);
                if (collisinOn == true){
                    direction = "right";
                    System.out.println("deneme9");
                }
            } else if (TopY<nextY && LeftX > nextX) {
                // down or left
                direction ="down";
                gp.cKontrol.kontrolZemin(this);
                if(collisinOn == true){
                    direction ="left";
                    System.out.println("deneme10");
                }

            }
            else if (TopY<nextY && LeftX < nextX) {
                // down or right
                direction ="down";
                gp.cKontrol.kontrolZemin(this);
                if(collisinOn == true){
                    direction ="right";
                }

            }
            //If reachea the goal, stop the search
            int nextCol = gp.yolBulma.pathList.get(0).col;
            int nextRow = gp.yolBulma.pathList.get(0).row;
            if(nextCol == goalCol && nextRow == goalRow) {

            }
        }
    }
}
