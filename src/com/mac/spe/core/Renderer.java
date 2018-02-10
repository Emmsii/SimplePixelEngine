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
        drawSprite(sprite, xp, yp, transparentColor, transparentColor);
    }
    
    public void drawSprite(Sprite sprite, int xp, int yp, int foregroundColor){
        drawSprite(sprite, xp, yp, foregroundColor, transparentColor);
    }
    
    public void drawSprite(Sprite sprite, int xp, int yp, int foregroundColor, int backgroundColor){
        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int col = sprite.getPixel(x, y);
                if(col != transparentColor) setPixel(xa, ya, foregroundColor == transparentColor ? col : foregroundColor);
                else if(backgroundColor != transparentColor) setPixel(xa, ya, backgroundColor);
            }
        }
    }
    
    public void fillBackgroundColor(int xp, int yp, int w, int h, int color){
        if(color == transparentColor) return;
        for(int y = 0; y < h; y++){
            int ya = y + yp;
            for(int x = 0; x < w; x++){
                int xa = x + xp;
                setPixel(xa, ya, color);
            }
        }
    }
    
    public void write(String text, Font font, int xp, int yp){
        write(text, font, xp, yp, transparentColor, transparentColor);
    }

    public void write(String text, Font font, int xp, int yp, int foregroundColor){
        write(text, font, xp, yp, foregroundColor, transparentColor);
    }
    
    public void write(String text, Font font, int xp, int yp, int foregroundColor, int backgroundColor){
        if(text == null || text.length() == 0) return;
        int xa;
        for(int i = 0; i < text.length(); i++){
            Sprite sprite = font.getCharacterSprite(text.charAt(i));
            xa = xp + (i * font.getCharWidth());
            if(sprite == null) fillBackgroundColor(xa, yp, font.getCharWidth(), font.getCharHeight(), backgroundColor);
            else drawSprite(sprite, xa, yp, foregroundColor, backgroundColor);
        }
    }
    
    public void clear(){
        clear(0);
    }
    
    public void clear(int color){
        for(int i = 0; i < pixels.length; i++) pixels[i] = color;
    }
    
}
