package com.mac.spe.rendering;

import com.mac.spe.graphics.Bitmap;
import com.mac.spe.graphics.Font;
import com.mac.spe.graphics.Sprite;
import com.mac.spe.helpers.ColorHelper;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:27 PM.
 */
public class Renderer extends Bitmap {

    /**
     * Setting the foreground or background colors in the drawSprite
     * methods to this value will result in the colors being ignored
     * and not used.
     */
    public static int ignoreColor = -1;

    /**
     * Pixels of this exact color will be considered transparent.
     */
    public static int transparentColor = -1;

    /**
     * Determines the positioning of sprites when drawn.
     * When the renderMode is set to {@link RenderMode#PRECISE}, sprites will
     * draw at the precise position specified, eg x = 32, y = 102.
     * When the renderMode is set to {@link RenderMode#TILED}, the position
     * specified will be multiplied by the width and height of the Sprite.
     * If the sprite width and height is 8 and x = 3, y = 5 then the
     * sprite will be drawn at x = 24, y = 40.
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
     * Internal method to handle drawing sprites to the screen with a 
     * specific foreground and background color. Setting the values
     * to {@link Renderer#ignoreColor} will result in the colors being
     * ignored.
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     * @param backgroundColor The hex color to override transparent pixels with.
     */
    private void drawSprite(Sprite sprite, int xp, int yp, int foregroundColor, int backgroundColor){
        if (renderMode == RenderMode.TILED) {
            xp *= sprite.getWidth();
            yp *= sprite.getHeight();
        }
        
        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                int col = sprite.getPixel(x, y);
                if (col != transparentColor) setPixel(xa, ya, foregroundColor == ignoreColor ? col : foregroundColor);
                else if (backgroundColor != ignoreColor) setPixel(xa, ya, backgroundColor);
            }
        }

    }

    /**
     * Draws a {@link Sprite} to the screen. If the {@link RenderMode} is
     * set to {@link RenderMode#TILED}, the position will be multiplied by the width and height
     * of the Sprite. A {@link RenderMode#PRECISE} will draw the Sprite at
     * the precise x and y coordinates.
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     */
    public void drawSprite(Sprite sprite, int xp, int yp) {
        drawSprite(sprite, xp, yp, ignoreColor, ignoreColor);
    }

    /**
     * Draws a {@link Sprite} to the screen. Overriding the non-transparent
     * pixels with a specific color. Specifying the override color as {@link Renderer#ignoreColor}
     * will result in it being ignored. If the {@link RenderMode} is set to
     * {@link RenderMode#TILED}, the position will be multiplied by the width and height of
     * the Sprite. A {@link RenderMode#PRECISE} will draw the Sprite at the
     * precise x and y coordinates.
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     */
    public void drawSpriteColored(Sprite sprite, int xp, int yp, int foregroundColor) {
        drawSpriteColored(sprite, xp, yp, foregroundColor, ignoreColor);
    }

    /**
     * Draws a {@link Sprite} to the screen. Overriding the non-transparent
     * and transparent pixels with specific colors. Specifying the override colors as
     * {@link Renderer#ignoreColor} will result in them being ignored. If the {@link RenderMode}
     * is set to {@link RenderMode#TILED}, the position will be multiplied by the width and 
     * height of the Sprite. A {@link RenderMode#PRECISE} will draw the Sprite
     * at the precise x and y coordinates.
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     * @param backgroundColor The hex color to override transparent pixels with.
     */
    public void drawSpriteColored(Sprite sprite, int xp, int yp, int foregroundColor, int backgroundColor){
        drawSprite(sprite, xp, yp, foregroundColor, backgroundColor);
    }

    /**
     * Draws a {@link Sprite} to the screen. Blending the original Sprite
     * colors with a new color based on a value between 0 and 1, with 0
     * being the original sprite color and 1 being the blended color. 
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     * @param blendColor The hex color to blend the original Sprite pixels with.
     * @param factor An amount between 0-1 ot blend between the two colors.
     */
    public void drawSpriteBlended(Sprite sprite, int xp, int yp, int blendColor, float factor) {
        drawSpriteBlended(sprite, xp, yp, blendColor, factor, ignoreColor, ignoreColor);
    }

    /**
     * Draws a {@link Sprite} to the screen. Overriding the non-transparent
     * pixels with a specific color. Specifying the override color as {@link Renderer#ignoreColor}
     * will result in it being ignored. Also blending between the specified
     * override color and the blend color, based on a value between 0 and 1.
     * With 0 being the specified override color and 1 being the blended color. 
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     * @param blendColor The hex color to blend the original Sprite pixels with.
     * @param factor An amount between 0-1 ot blend between the two colors.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     */
    public void drawSpriteBlended(Sprite sprite, int xp, int yp, int blendColor, float factor, int foregroundColor) {
        drawSpriteBlended(sprite, xp, yp, blendColor, factor, foregroundColor, ignoreColor);
    }

    /**
     * Draws a {@link Sprite} to the screen. Overriding the non-transparent and
     * transparent pixels with specific colors. Also blending between the 
     * specified override colors and the blend color, based on a value between 0
     * and 1. With 0 being the specified override colors and 1 being the blended
     * color.
     * @param sprite The Sprite to draw to the screen.
     * @param xp Position on the x axis to draw the Sprite.
     * @param yp Position on the y axis to draw the Sprite.
     * @param blendColor The hex color to blend the original Sprite pixels with.
     * @param factor An amount between 0-1 ot blend between the two colors.
     * @param foregroundColor The hex color to override non-transparent pixels with.
     * @param backgroundColor The hex color to override transparent pixels with.
     */
    public void drawSpriteBlended(Sprite sprite, int xp, int yp, int blendColor, float factor, int foregroundColor, int backgroundColor) {
        if (renderMode == RenderMode.TILED) {
            xp *= sprite.getWidth();
            yp *= sprite.getHeight();
        }

        for (int y = 0; y < sprite.getHeight(); y++) {
            int ya = y + yp;
            for (int x = 0; x < sprite.getWidth(); x++) {
                int xa = x + xp;
                int col = sprite.getPixel(x, y);
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
        if(renderMode == RenderMode.TILED){
            xp *= w;
            yp *= h;
        }
        
        if(color == transparentColor) return;
        for(int y = 0; y < h; y++){
            int ya = y + yp;
            for(int x = 0; x < w; x++){
                int xa = x + xp;
                setPixel(xa, ya, color);
            }
        }
    }

    /**
     * Draw a string to the screen. If the position of the characters
     * is out of screen bounds, they will be ignored. The text color
     * will be the same as is on the Font spritesheet. The {@link Renderer#renderMode}
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
            Sprite sprite = font.getCharacterSprite(text.charAt(i));
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

            if(sprite == null) continue;
            drawSpriteColored(sprite, xa, yp, foregroundColor, backgroundColor);
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
