package com.mac.spe.rendering;

import com.mac.spe.helpers.ColorHelper;
import com.mac.spe.rendering.graphics.Bitmap;
import com.mac.spe.rendering.graphics.Font;
import com.mac.spe.rendering.graphics.IDrawable;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:27 PM.
 */
public class Renderer extends Bitmap {

    /**
     * Setting the foreground or background colors in the draw
     * methods to this value will result in the colors being ignored
     * and not used. The default is -1.
     */
    public static int ignoreColor = -1;

    /**
     * Pixels of this exact color will be considered transparent.
     * The default is 0xff000000.
     */
    public static int transparentColor = 0xff000000;

    /**
     * Determines the positioning of drawables when drawn.
     * When the renderMode is set to {@link RenderMode#PRECISE}, drawables will
     * draw at the precise position specified, eg x = 32, y = 102.
     * When the renderMode is set to {@link RenderMode#TILED}, the position
     * specified will be multiplied by the width and height of the IDrawable.
     * If the drawable width and height is 8 and x = 3, y = 5 then the
     * drawable will be drawn at x = 24, y = 40.
     */
    private RenderMode renderMode = RenderMode.PRECISE;

    /**
     * Constructor for renderer class.
     * @param width The width in pixels of the screen.
     * @param height The height in pixels of the screen.
     */
    public Renderer(int width, int height){
        super(width, height);
    }

    /**
     * Internal method to handle drawing drawables to the screen with a 
     * specific foreground and background color. Setting the values
     * to {@link Renderer#ignoreColor} will result in the colors being
     * ignored.
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     * @param backgroundColor The hex color to override transparent pixels with.
     */
    private void draw(IDrawable drawable, int xp, int yp, int foregroundColor, int backgroundColor){
        if (renderMode == RenderMode.TILED) {
            xp *= drawable.getWidth();
            yp *= drawable.getHeight();
        }
        
        for (int y = 0; y < drawable.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < drawable.getWidth(); x++) {
                int xa = x + xp;
                int col = drawable.getPixel(x, y);
                if (col != transparentColor) setPixel(xa, ya, foregroundColor == ignoreColor ? col : foregroundColor);
                else if (backgroundColor != ignoreColor) setPixel(xa, ya, backgroundColor);
            }
        }

    }

    /**
     * Draws a {@link IDrawable} to the screen. If the {@link RenderMode} is
     * set to {@link RenderMode#TILED}, the position will be multiplied by the width and height
     * of the IDrawable. A {@link RenderMode#PRECISE} will draw the IDrawable at
     * the precise x and y coordinates.
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     */
    public void draw(IDrawable drawable, int xp, int yp) {
        draw(drawable, xp, yp, ignoreColor, ignoreColor);
    }

    /**
     * Draws a {@link IDrawable} to the screen. Overriding the non-transparent
     * pixels with a specific color. Specifying the override color as {@link Renderer#ignoreColor}
     * will result in it being ignored. If the {@link RenderMode} is set to
     * {@link RenderMode#TILED}, the position will be multiplied by the width and height of
     * the IDrawable. A {@link RenderMode#PRECISE} will draw the IDrawable at the
     * precise x and y coordinates.
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     */
    public void drawColored(IDrawable drawable, int xp, int yp, int foregroundColor) {
        drawColored(drawable, xp, yp, foregroundColor, ignoreColor);
    }

    /**
     * Draws a {@link IDrawable} to the screen. Overriding the non-transparent
     * and transparent pixels with specific colors. Specifying the override colors as
     * {@link Renderer#ignoreColor} will result in them being ignored. If the {@link RenderMode}
     * is set to {@link RenderMode#TILED}, the position will be multiplied by the width and 
     * height of the IDrawable. A {@link RenderMode#PRECISE} will draw the IDrawable
     * at the precise x and y coordinates.
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     * @param backgroundColor The hex color to override transparent pixels with.
     */
    public void drawColored(IDrawable drawable, int xp, int yp, int foregroundColor, int backgroundColor){
        draw(drawable, xp, yp, foregroundColor, backgroundColor);
    }

    /**
     * Draws a {@link IDrawable} to the screen. Blending the original IDrawable
     * colors with a new color based on a value between 0 and 1, with 0
     * being the original drawable color and 1 being the blended color. 
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     * @param blendColor The hex color to blend the original IDrawable pixels with.
     * @param factor An amount between 0-1 ot blend between the two colors.
     */
    public void drawBlended(IDrawable drawable, int xp, int yp, int blendColor, float factor) {
        drawBlended(drawable, xp, yp, blendColor, factor, ignoreColor, ignoreColor);
    }

    /**
     * Draws a {@link IDrawable} to the screen. Overriding the non-transparent
     * pixels with a specific color. Specifying the override color as {@link Renderer#ignoreColor}
     * will result in it being ignored. Also blending between the specified
     * override color and the blend color, based on a value between 0 and 1.
     * With 0 being the specified override color and 1 being the blended color. 
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     * @param blendColor The hex color to blend the original IDrawable pixels with.
     * @param factor An amount between 0-1 ot blend between the two colors.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     */
    public void drawBlended(IDrawable drawable, int xp, int yp, int blendColor, float factor, int foregroundColor) {
        drawBlended(drawable, xp, yp, blendColor, factor, foregroundColor, ignoreColor);
    }

    /**
     * Draws a {@link IDrawable} to the screen. Overriding the non-transparent and
     * transparent pixels with specific colors. Also blending between the 
     * specified override colors and the blend color, based on a value between 0
     * and 1. With 0 being the specified override colors and 1 being the blended
     * color.
     * @param drawable The IDrawable to draw to the screen.
     * @param xp Position on the x axis to draw the IDrawable.
     * @param yp Position on the y axis to draw the IDrawable.
     * @param blendColor The hex color to blend the original IDrawable pixels with.
     * @param factor An amount between 0-1 ot blend between the two colors.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     * @param backgroundColor The hex color to override transparent pixels with.
     */
    public void drawBlended(IDrawable drawable, int xp, int yp, int blendColor, float factor, int foregroundColor, int backgroundColor) {
        if (renderMode == RenderMode.TILED) {
            xp *= drawable.getWidth();
            yp *= drawable.getHeight();
        }

        for (int y = 0; y < drawable.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < drawable.getWidth(); x++) {
                int xa = x + xp;
                int col = drawable.getPixel(x, y);
                if (col != transparentColor) setPixel(xa, ya, ColorHelper.blend(foregroundColor == ignoreColor ? col : foregroundColor, blendColor, factor));
                else if (backgroundColor != ignoreColor) setPixel(xa, ya, ColorHelper.blend(backgroundColor, blendColor, factor));
            }
        }
    }
   
    /**
     * Fills a area with a specific color. The renderMode
     * setting effects the positioning of this method.
     * @param xp The x position of the area to fill.
     * @param yp The y position of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @param color The hex color to fill the area with. 
     */
    public void fillWithColor(int xp, int yp, int w, int h, int color){
        fillWithColor(xp, yp, w, h, color, 1f);
    }

    /**
     * Fills a area with a specific color. The renderMode
     * setting effects the positioning of this method. 
     * @param xp The x position of the area to fill.
     * @param yp The y position of the area to fill.
     * @param w The width of the area to fill.
     * @param h The height of the area to fill.
     * @param color The hex color to fill the area with. 
     * @param transparency The transparency of the fill color, will blend with previously drawn pixels.
     */
    public void fillWithColor(int xp, int yp, int w, int h, int color, float transparency){
        if(renderMode == RenderMode.TILED){
            xp *= w;
            yp *= h;
        }
        
        if(color == transparentColor || color == ignoreColor || transparency <= 0f) return;
        for(int y = 0; y < h; y++){
            int ya = y + yp;
            for(int x = 0; x < w; x++){
                int xa = x + xp;
                setPixel(xa, ya, ColorHelper.blend(getPixel(xa, ya), color, transparency));
            }
        }
    }

    /**
     * Draw a string to the screen. If the position of the characters
     * is out of screen bounds, they will be ignored. The text color
     * will be the same as is on the Font drawablesheet. The {@link Renderer#renderMode}
     * effects the positioning of the text.
     * @param text The string to draw to the screen.
     * @param font The font to use.
     * @param xp The x position of the text.
     * @param yp The y position of the text.
     */
    public void write(String text, Font font, int xp, int yp){
        write(text, font, xp, yp, ignoreColor, ignoreColor);
    }

    public void write(String text, Font font, int xp, int yp, byte align){
        write(text, font, xp, yp, ignoreColor, ignoreColor, align);
    }

    /**
     * Draw a string to the screen. If the position of the characters
     * is out of screen bounds, they will be ignored. The text color
     * is specified by the foregroundColor parameter. The {@link Renderer#renderMode}
     * effects the positioning of the text.
     * @param text The string to draw to the screen.
     * @param font The font to use.
     * @param xp The x position of the text.
     * @param yp The y position of the text.
     * @param foregroundColor The hex color of the font.
     */
    public void write(String text, Font font, int xp, int yp, int foregroundColor){
        write(text, font, xp, yp, foregroundColor, ignoreColor);
    }

    public void write(String text, Font font, int xp, int yp, int foregroundColor, byte align){
        write(text, font, xp, yp, foregroundColor, ignoreColor, align);
    }

    /**
     * Draw a string to the screen. If the position of the characters
     * is out of screen bounds, they will be ignored. The text foreground
     * and background colors are specified as parameters. The {@link Renderer#renderMode}
     * effects the positioning of the text.
     * @param text The string to draw to the screen.
     * @param font The font to use.
     * @param xp The x position of the text.
     * @param yp The y position of the text.
     * @param foregroundColor The hex color of the font.
     * @param backgroundColor The hex color of the background of each character.
     */
    public void write(String text, Font font, int xp, int yp, int foregroundColor, int backgroundColor){
        write(text, font, xp, yp, foregroundColor, backgroundColor, Font.ALIGN_LEFT);
    }

    public void write(String text, Font font, int xp, int yp, int foregroundColor, int backgroundColor, byte align){
        if(text == null || text.length() == 0) return;

        for(int i = 0; i < text.length(); i++){
            IDrawable drawable = font.getCharacterSprite(text.charAt(i));
            int xa = xp + (renderMode == RenderMode.PRECISE ? i * font.getCharWidth() : i);
            switch (align){
                case Font.ALIGN_CENTER:
                    xa -= ((text.length() / 2) * font.getCharWidth());
                    break;
                case Font.ALIGN_RIGHT:
                    xa -= text.length() * font.getCharWidth();
                    break;
                case Font.ALIGN_LEFT:
                default:
                    break;
            }

            if(drawable == null) continue;
            drawColored(drawable, xa, yp, foregroundColor, backgroundColor);
        }
    }
    
    /**
     * Clear the screen with black.
     */
    public void clear(){
        clear(0);
    }

    /**
     * Clear the screen with a specific color.
     * @param color The hex color to clear the screen with.
     */
    public void clear(int color){
        for(int i = 0; i < pixels.length; i++) setPixel(i, color);
    }

    /**
     * Sets the renderMode of the {@link Renderer}. See the {@link Renderer#renderMode}
     * doc on what renderMode does.
     * @param renderMode
     */
    public void setRenderMode(RenderMode renderMode){
        this.renderMode = renderMode;
    }
    
}
