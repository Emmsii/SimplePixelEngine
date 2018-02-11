package com.mac.spe.window;

import com.mac.spe.rendering.Renderer;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 11/02/2018 at 08:26 AM.
 */
public class Panel extends Canvas{
    
    private final int width, height;
    private final int scale;
    private final Renderer renderer;
    
    private BufferedImage image;
    
    public Panel(int width, int height, int scale, Renderer renderer){
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.renderer = renderer;
        
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        renderer.setPixels(((DataBufferInt) image.getRaster().getDataBuffer()).getData());
        
        Dimension size = new Dimension(getWidthInPixels(), getHeightInPixels());
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }
    
    public void render() {
        if(renderer == null) throw new IllegalStateException("Renderer cannot be null.");
        
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(2);
            requestFocus();
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidthInPixels(), getHeightInPixels(), null);
        
        g.dispose();
        bs.show();
    }
    
    public int getWidthInPixels(){
        return width * scale;
    }
    
    public int getHeightInPixels(){
        return  height * scale;
    }
}
