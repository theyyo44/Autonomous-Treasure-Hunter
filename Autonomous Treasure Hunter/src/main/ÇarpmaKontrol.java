package main;

import javax.swing.text.html.parser.Entity;
import entity.entity;


import java.awt.*;
import java.io.IOException;

public class ÇarpmaKontrol {

    GamePanel gp;

    public ÇarpmaKontrol(GamePanel gp){

        this.gp = gp;

    }

    public void kontrolZemin(entity entity){

        int entityLeftWorldX = entity.worldx + entity.solidArea.x;
        int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldy + entity.solidArea.y;
        int entityBotWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBotRow = entityBotWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction){

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisinOn = true;
                }
                break;
            case "down":
                entityBotRow = (entityBotWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityBotRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBotRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisinOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTileNumber[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityLeftCol][entityBotRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisinOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;

                tileNum1 = gp.tileM.mapTileNumber[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNumber[entityRightCol][entityBotRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entity.collisinOn = true;
                }
                break;
        }
    }

     public int KontrolSandık (entity entity, boolean player){
        int indeks = 999;
        try {

            for (int i = 0; i < gp.sandık.length; i++) {

                if (gp.sandık[i] != null) {
                    //entitynin çevre posziyonunu al
                    entity.solidArea.x = (entity.worldx + entity.solidArea.x);
                    entity.solidArea.y = (entity.worldy + entity.solidArea.y);
                    //sandığın cevre poziyonunu alma
                    gp.sandık[i].solidArea.x = (gp.sandık[i].worldX + gp.sandık[i].solidArea.x);
                    gp.sandık[i].solidArea.y = (gp.sandık[i].worldY + gp.sandık[i].solidArea.y);


                    switch (entity.direction) {
                        case "up":
                            entity.solidArea.y -= entity.speed;
                            if (entity.solidArea.intersects(gp.sandık[i].solidArea)) {
                                if (gp.sandık[i].collision == true) {
                                    entity.collisinOn = true;
                                }
                                if (player == true) {
                                    indeks = i;
                                }
                            }
                            break;
                        case "down":
                            entity.solidArea.y += entity.speed;
                            if (entity.solidArea.intersects(gp.sandık[i].solidArea)) {
                                if (gp.sandık[i].collision == true) {
                                    entity.collisinOn = true;
                                }
                                if (player == true) {
                                    indeks = i;
                                }
                            }
                            break;
                        case "left":
                            entity.solidArea.x -= entity.speed;
                            if (entity.solidArea.intersects(gp.sandık[i].solidArea)) {
                                if (gp.sandık[i].collision == true) {
                                    entity.collisinOn = true;
                                }
                                if (player == true) {
                                    indeks = i;
                                }
                            }
                            break;
                        case "right":
                            entity.solidArea.x += entity.speed;
                            if (entity.solidArea.intersects(gp.sandık[i].solidArea)) {
                                if (gp.sandık[i].collision == true) {
                                    entity.collisinOn = true;
                                }
                                if (player == true) {

                                    indeks = i;
                                }

                            }
                            break;

                    }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gp.sandık[i].solidArea.x = gp.sandık[i].solidAreaDefaultX;
                    gp.sandık[i].solidArea.y = gp.sandık[i].solidAreaDefaultY;
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
         return indeks;
     }
}
