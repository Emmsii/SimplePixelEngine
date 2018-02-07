package com.mac.spe.graphics;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:27 PM.
 */
public class Renderer extends Bitmap{
    
    public Renderer(int width, int height){
        super(width, height);
    }
    
    public void drawSprite(Sprite sprite, int xp, int yp){
        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;
            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                setPixel(xa, ya, sprite.getPixel(x, y));
            }
        }
    }
    
    public void clear(){
        clear(0);
    }
    
    public void clear(int color){
        for(int i = 0; i < pixels.length; i++) pixels[i] = color;
    }
    
}
