package main;

import Sandıklar.AltınSandık;
import Sandıklar.BronzeSandık;
import Sandıklar.GümüsSandık;
import Sandıklar.ZümrütSandık;
import engeller.*;


import java.util.Random;
public class AssetSetter {
    GamePanel gp;

    Random rand = new Random();
    public AssetSetter(GamePanel gp){
        this.gp=gp;

    }

    public void setObject(){
        int x,y;
        // Engeller

        // AĞAÇ EKLEME
        for (int i = 0; i < 20; i++) {
            gp.engel[i] = new Agac();
            boolean temasVar;
            do {
                temasVar = false;
                x = rand.nextInt(harita.HaritaBoyutu-2) + 1;
                y = rand.nextInt(harita.HaritaBoyutu-3) + 1;



                for (int dx = 0; dx < 2; dx++) {
                    for (int dy = 0; dy < 3; dy++) {
                        if (gp.tileM.mapTileNumber[x + dx][y + dy] == 1 || gp.tileM.mapTileNumber[x + dx][y + dy] == 2) {
                            temasVar = true;
                            break;
                        }
                    }
                    if (temasVar) {
                        break;
                    }
                }
            } while (temasVar);
            gp.engel[i].worldX = x *gp.tileSize;
            gp.engel[i].worldY = y *gp.tileSize;
            if (gp.engel[i].worldX/gp.tileSize<=25){
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 2; k++) {
                        gp.tileM.mapTileNumber[(gp.engel[i].worldX/gp.tileSize)+j][(gp.engel[i].worldY/gp.tileSize)+k] = 2;
                    }
                }
            }else {
                for (int j = 0; j < 2; j++) {
                    for (int k = 0; k < 3; k++) {
                        gp.tileM.mapTileNumber[(gp.engel[i].worldX/gp.tileSize)+j][(gp.engel[i].worldY/gp.tileSize)+k] = 2;
                    }
                }
            }

        }

            // DAĞ EKLME
        for (int i = 20; i < 35; i++) {
            gp.engel[i] = new Dağ();
            boolean temasVar;
            do {
                temasVar = false;
                x = rand.nextInt(harita.HaritaBoyutu-6) + 1;
                y = rand.nextInt(harita.HaritaBoyutu-6) + 1;

                // 5x5 boyutundaki engel koordinatlarını kontrol et

                for (int dx = 0; dx < 6; dx++) {
                    for (int dy = 0; dy < 6; dy++) {
                        if (gp.tileM.mapTileNumber[x + dx][y + dy] == 1 || gp.tileM.mapTileNumber[x + dx][y + dy] == 2) {
                            temasVar = true;
                            break;
                        }
                    }
                    if (temasVar) {
                        break;
                    }
                }
            } while (temasVar);

            gp.engel[i].worldX = x *gp.tileSize;
            gp.engel[i].worldY = y *gp.tileSize;

            for (int j = 0; j < 5; j++) {
                for (int k = 0; k <5; k++) {

                    gp.tileM.mapTileNumber[(gp.engel[i].worldX/gp.tileSize)+j][(gp.engel[i].worldY/gp.tileSize)+k] = 2;
                }

            }
        }
        //DUVAR EKLEME
        for (int i = 35; i < 50; i++) {
            gp.engel[i] = new Duvar();
            boolean temasVar;
            do {
                temasVar = false;
                x = rand.nextInt(harita.HaritaBoyutu-2) + 1;
                y = rand.nextInt(harita.HaritaBoyutu-12) + 1;

                // 5x5 boyutundaki engel koordinatlarını kontrol et

                for (int dx = 0; dx < 1; dx++) {
                    for (int dy = 0; dy < 10; dy++) {
                        if (gp.tileM.mapTileNumber[x + dx][y + dy] == 1 || gp.tileM.mapTileNumber[x + dx][y + dy] == 2) {
                            temasVar = true;
                            break;
                        }
                    }
                    if (temasVar) {
                        break;
                    }
                }
            } while (temasVar);

            gp.engel[i].worldX = x *gp.tileSize;
            gp.engel[i].worldY = y *gp.tileSize;

            for (int j = 0; j < 1; j++) {
                for (int k = 0; k <10; k++) {
                    if (gp.engel[i].worldX/gp.tileSize<=harita.HaritaBoyutu/2){
                        gp.tileM.mapTileNumber[(gp.engel[i].worldX/gp.tileSize)+j][(gp.engel[i].worldY/gp.tileSize)+k] = 4;
                    }else {
                        gp.tileM.mapTileNumber[(gp.engel[i].worldX/gp.tileSize)+j][(gp.engel[i].worldY/gp.tileSize)+k] = 5;
                    }

                }

            }
        }

        //SASNDIKLAR
        for (int i = 0; i < 20; i++) {
            if (i < 5) {
                gp.sandık[i] = new AltınSandık();
            } else if (i < 10) {
                gp.sandık[i] = new GümüsSandık();
            } else if (i < 15) {
                gp.sandık[i] = new ZümrütSandık();
            } else {
                gp.sandık[i] = new BronzeSandık();
            }

            do {
                x = rand.nextInt(harita.HaritaBoyutu);
                y = rand.nextInt(harita.HaritaBoyutu);
            } while (gp.tileM.mapTileNumber[x][y] == 1|| gp.tileM.mapTileNumber[x][y] == 2|| gp.tileM.mapTileNumber[x][y] == 4);

            gp.sandık[i].worldX = x * gp.tileSize;
            gp.sandık[i].worldY = y * gp.tileSize;
        }




    }

    public  void setNPC() {

        int x,y;

        //ARI
        for (int i = 0; i <10 ; i++) {
            gp.npc[i] = new Arı(gp);
            do {
                x = rand.nextInt(harita.HaritaBoyutu-3);
                y = rand.nextInt(harita.HaritaBoyutu);
            }while (gp.tileM.mapTileNumber[x][y] == 1|| gp.tileM.mapTileNumber[x+1][y] == 1||gp.tileM.mapTileNumber[x+2][y] == 1 || gp.tileM.mapTileNumber[x][y] == 2|| gp.tileM.mapTileNumber[x+1][y] == 2||gp.tileM.mapTileNumber[x+2][y] == 2);

            gp.npc[i].worldX = x * gp.tileSize;
            gp.npc[i].worldY = y * gp.tileSize;
            gp.tileM.mapTileNumber[x+1][y] =1;
            gp.tileM.mapTileNumber[x+2][y] =1;
            gp.tileM.mapTileNumber[x][y] =1;

        }

       //KUŞ
        for (int i = 10; i < 20; i++) {
            gp.npc[i] = new Kus(gp);
            boolean temasVar;
            do {
                temasVar = false;
                x = rand.nextInt(harita.HaritaBoyutu-2) + 1;
                y = rand.nextInt(harita.HaritaBoyutu-6) + 1;



                for (int dx = 0; dx < 1; dx++) {
                    for (int dy = 0; dy < 5; dy++) {
                        if (gp.tileM.mapTileNumber[x + dx][y + dy] == 1 || gp.tileM.mapTileNumber[x + dx][y + dy] == 2) {
                            temasVar = true;
                            break;
                        }
                    }
                    if (temasVar) {
                        break;
                    }
                }
            } while (temasVar);

            gp.npc[i].worldX = x *gp.tileSize;
            gp.npc[i].worldY = y *gp.tileSize;

            for (int j = 0; j < 1; j++) {
                for (int k = 0; k <5; k++) {

                    gp.tileM.mapTileNumber[(gp.npc[i].worldX/gp.tileSize)+j][(gp.npc[i].worldY/gp.tileSize)+k] = 1;
                }

            }
        }

    }

}
