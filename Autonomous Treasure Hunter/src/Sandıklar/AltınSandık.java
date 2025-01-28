package Sandıklar;

import javax.imageio.ImageIO;
import java.io.IOException;

public class AltınSandık extends Sandıklar{
    public AltınSandık(){
        name ="Altın Sandık";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/sandıkpng/altın_sandık.png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
