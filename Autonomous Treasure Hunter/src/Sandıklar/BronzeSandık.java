package Sandıklar;

import javax.imageio.ImageIO;
import java.io.IOException;

public class BronzeSandık extends Sandıklar{
    public BronzeSandık(){
        name ="Bronze Sandık";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sandıkpng/bronze_sandık.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
