package com.mac.spe.core;

import com.mac.spe.graphics.Bitmap;
import com.mac.spe.graphics.Font;
import com.mac.spe.graphics.Sprite;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:27 PM.
 */
public class Renderer extends Bitmap {
    
    public static int transparentColor = -1;
    
    public Renderer(int width, int height){
        super(width, height);
    }
    
    public void drawSprite(Sprite sprite, int xp, int yp){
        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int col = sprite.getPixel(x, y);
                if(col != transparentColor) setPixel(xa, ya, sprite.getPixel(x, y));
            }
        }
    }

    public void write(String text, Font font, int xp, int yp){
        if(text == null || text.length() == 0) return;
        
        for(int i = 0; i < text.length(); i++){
            int c = font.getCharacters().indexOf(text.charAt(i));

            if(c < 0) continue;
            int x = (c % 16);
            int y = (c / 16);
            
            Sprite sprite = font.getCharacterSprite(x, y);
            if(sprite == null) continue;
            
            drawSprite(sprite, xp + (i * font.getCharWidth()), yp);
        }
    }
    
    public void clear(){
        clear(0);
    }
    
    public void clear(int color){
        for(int i = 0; i < pixels.length; i++) pixels[i] = color;
    }
    
}
