package com.mac.spe.window;

import com.mac.spe.core.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:31 PM.
 */
public class Panel extends JPanel{
    
    private final int width, height;
    private final int scale;
    private final Renderer renderer;
    
    private BufferedImage image;
    private int[] pixels;
    
    public Panel(int width, int height, int scale, Renderer renderer){
        if(width <= 0) throw new IllegalArgumentException("Panel width must be greater than 0.");
        if(height <= 0) throw new IllegalArgumentException("Panel height must be greater than 0.");
        if(scale <= 0) throw new IllegalArgumentException("Panel scale must be greater than 0.");
        if(renderer == null) throw new IllegalArgumentException("Panel must have a Renderer.");
        
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.renderer = renderer;

        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        setPreferredSize(new Dimension(getWidthInPixels(), getHeightInPixels()));
    }

    @Override
    public void paint(Graphics g) {
        if(renderer == null) return;
        
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        for(int i = 0; i < pixels.length; i++) pixels[i] = renderer.getPixel(i);
        
        g.drawImage(image, 0, 0, getWidthInPixels(), getHeightInPixels(), null);
        
//        g.drawImage(image,
//                (getWidth() - getWidthInPixels() * scale) / 2,
//                (getHeight() - getHeightInPixels() * scale) / 2,
//                getWidthInPixels() * scale,
//                getHeightInPixels() * scale,
//                null);
    }

    public int getWidthInPixels(){
        return width * scale;
    }
    
    public int getHeightInPixels(){
        return height * scale;
    }
}
