package com.mac.spe.rendering.graphics;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:53 PM.
 */
public class Sprite implements IDrawable{

    private final Spritesheet spritesheet;
    private final int x, y;
    private final int xIndex, yIndex;
    private final int width, height;

    public Sprite(Spritesheet spritesheet, int x, int y, int width, int height){
        this.spritesheet = spritesheet;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        xIndex = x * width;
        yIndex = y * height;
    }

    @Override
    public int getPixel(int xp, int yp){
        return spritesheet.getPixel(xIndex + xp, yIndex + yp);
    }

    @Override
    public int getWidth(){
        return width;
    }

    @Override
    public int getHeight(){
        return height;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getXIndex(){
        return xIndex;
    }

    public int getYIndex(){
        return yIndex;
    }
}
