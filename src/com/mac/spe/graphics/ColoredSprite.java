package com.mac.spe.graphics;

import com.mac.spe.rendering.Renderer;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 08/02/2018 at 05:33 PM.
 */
public class ColoredSprite extends Sprite{

    private int foregroundColor = -1;
    private int backgroundColor = -1;

    public ColoredSprite(int width, int height, int[] pixels, int foregroundColor, int backgroundColor) {
        super(width, height, pixels);
        this.foregroundColor = foregroundColor;
        this.backgroundColor = backgroundColor;
    }

    @Override
    public int getPixel(int index) {
        int col = super.getPixel(index);
        if (col == Renderer.transparentColor && backgroundColor != -1) return backgroundColor;
        if (col != Renderer.transparentColor && foregroundColor != -1) return foregroundColor;
        return col;
    }
}
