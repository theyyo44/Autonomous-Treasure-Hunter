package Sandıklar;

import javax.imageio.ImageIO;
import java.io.IOException;

public class GümüsSandık extends Sandıklar{
    public GümüsSandık(){
        name ="Gümüş Sandık";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sandıkpng/gumus_sandık.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
