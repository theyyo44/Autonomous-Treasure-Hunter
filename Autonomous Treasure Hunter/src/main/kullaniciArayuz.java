package main;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import Sandıklar.Sandıklar;
import Sandıklar.AltınSandık;
import Sandıklar.BronzeSandık;
import Sandıklar.GümüsSandık;
import Sandıklar.ZümrütSandık;
import entity.Player;


public class kullaniciArayuz {

    GamePanel gp;
    Font arial_40 ,arial_80, arial_10;

    BufferedImage sandıkResim[] = new BufferedImage[4];

    public boolean mesajOn = false;
    private List<String> mesajlar = new ArrayList<>();

    int mesajSayac = 0;
    public boolean oyunBitti = false;
    double oyunZamani;
    int gümüşI = 0;
    int zümrütI = 0;

    DecimalFormat format = new DecimalFormat("#0.00");

    public kullaniciArayuz (GamePanel gp) {
        this.gp = gp;
        this.arial_10 = new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 10);
        this.arial_40 = new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT, 20);
        this.arial_80 = new Font("Arial", Font.LAYOUT_RIGHT_TO_LEFT,30);
        Sandıklar[] sandıklar = new Sandıklar[4];

        // Farklı alt sınıflardan oluşturulan nesneleri diziye atama
        sandıklar[0] = new AltınSandık();
        sandıklar[1] = new GümüsSandık();
        sandıklar[2] = new ZümrütSandık();
        sandıklar[3] = new BronzeSandık();

        sandıkResim[0] = sandıklar[0].image;
        sandıkResim[1] = sandıklar[1].image;
        sandıkResim[2] = sandıklar[2].image;
        sandıkResim[3] = sandıklar[3].image;
    }
    public void mesajgoster(String metin){

        if(metin.startsWith("Altın")){
            mesajlar.add(0,metin);
            gümüşI ++;
            zümrütI ++;
        }else if (metin.startsWith("Gümüş")){
            mesajlar.add(gümüşI,metin);
            zümrütI++;
        } else if (metin.startsWith("Zümrüt")) {
            mesajlar.add(zümrütI,metin);
        }else {
            mesajlar.add(metin);
        }

        mesajOn = true;

    }
    public void draw(Graphics2D g2) {

        if (oyunBitti == true){


            g2.setFont(arial_80);
            g2.setColor(Color.RED);

            String metin;
            int metinuzunluk;
            //oyun bitti mesajı
            metin = "!!Oyun Bitti!!";
            metinuzunluk = (int)g2.getFontMetrics().getStringBounds(metin,g2).getWidth();
            int x = gp.screenWidth/2-metinuzunluk/2;
            int y = gp.screenHeight/2- (gp.tileSize*5);
            g2.drawString(metin, x, y);


            //süre mesajı
            metin = "Süre : " + format.format(oyunZamani);
            metinuzunluk = (int)g2.getFontMetrics().getStringBounds(metin,g2).getWidth();
            x = gp.screenWidth/2-metinuzunluk/2;
            y = gp.screenHeight/2- (gp.tileSize*4);
            g2.drawString(metin, x, y);

            //Karakter ID
            metin = "Karakter ID : " + Player.ID;
            metinuzunluk = (int)g2.getFontMetrics().getStringBounds(metin,g2).getWidth();
            x = gp.screenWidth/2-metinuzunluk/2;
            y = gp.screenHeight/2- (gp.tileSize*3);
            g2.drawString(metin, x, y);

            //Karakter Name
            metin = "Karakter Name: " + Player.ad;
            metinuzunluk = (int)g2.getFontMetrics().getStringBounds(metin,g2).getWidth();
            x = gp.screenWidth/2-metinuzunluk/2;
            y = gp.screenHeight/2- (gp.tileSize*2);
            g2.drawString(metin, x, y);
            // ADIM SAYISI
            metin = "Adım Sayısı : " + gp.player.geçilenYollar.size();
            metinuzunluk = (int)g2.getFontMetrics().getStringBounds(metin,g2).getWidth();
            x = gp.screenWidth/2-metinuzunluk/2;
            y = gp.screenHeight/2- (gp.tileSize*1);
            g2.drawString(metin, x, y);
            gp.gameThread = null;



        }else {

            g2.setFont(arial_40);
            g2.setColor(Color.red);

            g2.drawImage(sandıkResim[0],1, 1, gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasASandık, 25,18);

            g2.drawImage(sandıkResim[1],1, 24, gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasGSandık, 25, 40);

            g2.drawImage(sandıkResim[2],1, 47, gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasZSandık, 25, 62);

            g2.drawImage(sandıkResim[3],1, 70, gp.tileSize,gp.tileSize,null);
            g2.drawString("x" + gp.player.hasBSandık, 25, 84);

            //ZAMAN
            oyunZamani += (double)1/60;
            g2.drawString("Zaman:"+ format.format(oyunZamani),gp.tileSize*17+25,gp.tileSize);

            // mesaj

            if(mesajOn == true){
                g2.setFont(arial_10);
                g2.setColor(Color.red);
                gp.setFont(g2.getFont().deriveFont(30F));

                int y = gp.tileSize * 5; // Yüksekliğini belirlemek için başlangıç noktası

                for (String mesaj : mesajlar) {
                    g2.drawString(mesaj, gp.tileSize / 2, y);
                    y += gp.tileSize; // Yeni mesajın başlangıç noktasını bir sonraki satıra taşı
                }



//                mesajSayac++;
//
//                if(mesajSayac > 240){
//                    mesajSayac = 0;
//                    mesajOn = false;
//                }
            }
        }


    }
}
