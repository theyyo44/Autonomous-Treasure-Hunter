package main;

import com.sun.source.tree.NewArrayTree;
import entity.Player;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class Giriş implements ActionListener {

    GamePanel gp;
    JFrame girişFrame = new JFrame();
    JButton Haritabuton = new JButton("Harita oluştur");
    JTextField PlayerIdField = new JTextField();
    JTextField PlayerNameField  = new JTextField();
    JTextField HaritaField = new JTextField();

    JLabel PlayerId = new JLabel("Oyuncu Id:");
    JLabel PlayerName = new JLabel("Oyuncu Adı:");
    JLabel Harita = new JLabel("Harita Boyutunu Giriniz:");


    Giriş(){

        PlayerId.setBounds(100,100,75,25);
        PlayerName.setBounds(100,200,75,25);
        Harita.setBounds(100,300,150,25);
        PlayerIdField.setBounds(250,100,200,25);
        PlayerNameField.setBounds(250,200,200,25);
        HaritaField.setBounds(250,300,200,25);
        Haritabuton.setBounds(175,400,200,25);
        Haritabuton.addActionListener(this);
        girişFrame.add(PlayerId);
        girişFrame.add(PlayerName);
        girişFrame.add(Harita);
        girişFrame.add(PlayerIdField);
        girişFrame.add(PlayerNameField);
        girişFrame.add(HaritaField);
        girişFrame.add(Haritabuton);

        girişFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        girişFrame.setSize(600,600);
        girişFrame.setLayout(null);
        girişFrame.setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==Haritabuton){
            Player.ID = PlayerIdField.getText();
            Player.ad = PlayerNameField.getText();


            harita.HaritaBoyutu=Integer.parseInt(HaritaField.getText());

            FileWriter writer = null;
            try {
                writer = new FileWriter("C:\\Users\\eyupe\\OneDrive\\Masaüstü\\Ensar\\untitled3\\res\\map\\map.txt");
                for (int i = 0; i < harita.HaritaBoyutu; i++) {
                    for (int j = 0; j < harita.HaritaBoyutu; j++) {
                        writer.write("0 "); // Her değeri bir boşlukla ayırarak yazın
                    }
                    writer.write("\n"); // Her satırın sonuna bir satır sonu karakteri (\n) ekleyin
                }

                writer.close(); // Dosyayı kapat
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }






            girişFrame.setVisible(false);
            JFrame window = new JFrame() ;
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("2D adventure");

            GamePanel gamePanel = new GamePanel();
            window.add(gamePanel);

            window.pack();

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.setUpGame();
            gamePanel.startGameThread();

        }

    }
}
