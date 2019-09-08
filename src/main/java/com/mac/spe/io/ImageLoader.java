package com.mac.spe.io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:56 PM.
 */
public class ImageLoader {
    
    private ImageLoader(){}
    
    public static BufferedImage load(String filePath){
        try {
            if (!new File(filePath).exists()) throw new FileNotFoundException("Could not find file '" + filePath + "'");
            return ImageIO.read(new FileInputStream(filePath));
//            InputStream in = ImageLoader.class.getClassLoader().getResourceAsStream(filePath);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        
        return null;
    }
}
