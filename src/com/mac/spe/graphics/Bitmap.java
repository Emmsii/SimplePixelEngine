package com.mac.spe.graphics;

import java.awt.image.BufferedImage;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:48 PM.
 */
public abstract class Bitmap {

    protected final int width, height;
    protected int[] pixels;
    
    public Bitmap(BufferedImage image){
        this(image.getWidth(), image.getHeight(), new int[image.getWidth() * image.getHeight()]);
        image.getRGB(0, 0, width, height, pixels, 0, width);
    }
    
    public Bitmap(int width, int height){
        this(width, height, new int[width * height]);
    }
    
    public Bitmap(int width, int height,int[] pixels){
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
    
    public void setPixel(int x, int y, int color){
        if(!inBounds(x, y)) return;
        pixels[x + y * width] = color;
    }
    
    public void setPixel(int index, int color){
        if(!inBounds(index)) return;
        pixels[index] = color;
    }
    
    public void setPixels(int[] pixels){
        this.pixels = pixels;
    }
    
    public int getPixel(int x, int y){
        return getPixel(x + y * width);
    }

    public int getPixel(int index){
        if(!inBounds(index)) return 0;
        return pixels[index];
    }
    
    public int[] getPixels(){
        return pixels;
    }
    
    public boolean inBounds(int x, int y){
        return x >= 0 && y >= 0 && x < width && y < height;
    }
    
    public boolean inBounds(int index){
        return index >= 0 && index < pixels.length;
    }

}
