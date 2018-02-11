package com.mac.spe.graphics;

import com.mac.spe.rendering.Renderer;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:53 PM.
 */
public class Sprite extends Bitmap{
    
    public Sprite(int width, int height, int[] pixels) {
        super(width, height, pixels);
    }
    
    public static Sprite cutFromSpritesheet(Spritesheet sheet, int xp, int yp, int w, int h){
        xp *= w;
        yp *= h;
        
        int[] spritePixels = new int[w * h];
        
        for(int y = 0; y < h; y++){
            int ya = y + yp;
            for(int x = 0; x < w; x++){
                int xa = x + xp;
                spritePixels[x + y * w] = sheet.getPixel(xa, ya);
            }
        }
        
        return new Sprite(w, h, spritePixels);
    }
    
    public Sprite overrideColors(int foregroundColor, int backgroundColor){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++) {
                int col = getPixel(x, y);
                if (col == Renderer.transparentColor && backgroundColor != -1) setPixel(x, y, backgroundColor);
                else if (col != Renderer.transparentColor && foregroundColor != -1) setPixel(x, y, foregroundColor);
                else setPixel(x, y, col);
            }
        }
        
        return this;
    }
    
}
