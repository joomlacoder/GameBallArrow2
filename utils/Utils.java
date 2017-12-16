package utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Created by Andrej on 12.12.2017.
 */
public class Utils {

    public static BufferedImage reSize (BufferedImage image, int width, int hieght){
        BufferedImage newImage = new BufferedImage(width, hieght, BufferedImage.TYPE_INT_ARGB);
        newImage.getGraphics().drawImage(image, 0, 0, width, hieght, null);
        return newImage;
    }

    //public static Integer[][] levelParser(String filePath){
      //  return result;
    //}

    public static final Integer[] str2int_arrays(String[] sArr){
        Integer[] result = new Integer[sArr.length];
        for (int i = 0; i < sArr.length; i++)
            result[i] = Integer.parseInt(sArr[i]);
        return result;
    }
}