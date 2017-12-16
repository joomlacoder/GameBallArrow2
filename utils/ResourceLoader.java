package utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * Created by Andrej on 12.12.2017.
 */
public class ResourceLoader {

    public static final String              PATH            = "res/";

    public static BufferedImage loadImage(String fileName){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(PATH + fileName));
        }catch (IOException e){
            System.err.println("IO img: ");
            e.printStackTrace();
        }
        return image;
    }
}
