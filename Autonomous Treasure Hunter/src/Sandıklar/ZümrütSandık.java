package Sandıklar;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ZümrütSandık extends Sandıklar {
    public ZümrütSandık(){
        name ="Zümrüt Sandık";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sandıkpng/zumrut_sandık.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
