package com.mac.spe.core;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 17/02/2018 at 09:09 AM.
 */
public class EngineConfig {
    
    private final int width, height;
    private final String title;
    private int scale;
    
    private boolean fullscreen;
    
    private double targetFramesPerSecond = 60.0;
    private boolean uncappedFrameRate = false;
    
    public EngineConfig(int width, int height, String title){
        if(width <= 0) throw new IllegalArgumentException("Width must be greater than 0.");
        if(height <= 0) throw new IllegalArgumentException("Height must be greater than 0.");
        
        this.width = width;
        this.height = height;
        this.title = title;
    }
    
    public void setScale(int scale){
        if(scale <= 0) throw new IllegalArgumentException("Scale must be greater than 0.");
        this.scale = scale;
    }

    public void setFullscreen(boolean fullscreen){
        this.fullscreen = fullscreen;
    }
    
    public void setTargetFramesPerSecond(double targetFramesPerSecond){
        if(targetFramesPerSecond <= 0) throw new IllegalArgumentException("Target frames per second must be greater than 0.");
        this.targetFramesPerSecond = targetFramesPerSecond;
    }
    
    public void setUncappedFrameRate(boolean uncappedFrameRate){
        this.uncappedFrameRate = uncappedFrameRate;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getTitle() {
        return title;
    }

    public int getScale() {
        return scale;
    }
    
    public boolean getFullscreen(){
        return fullscreen;
    }

    public double getTargetFramesPerSecond() {
        return targetFramesPerSecond;
    }

    public boolean isUncappedFrameRate() {
        return uncappedFrameRate;
    }
}
