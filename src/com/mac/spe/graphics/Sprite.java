package com.mac.spe.graphics;

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
    
    public ColoredSprite convertToColoredSprite(int foregroundColor, int backgroundColor){
        return new ColoredSprite(width, height, pixels, foregroundColor, backgroundColor);
    }
}
