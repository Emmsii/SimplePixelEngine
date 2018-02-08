package com.mac.spe.graphics;

import java.awt.image.BufferedImage;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 08/02/2018 at 08:28 PM.
 */
public class Font extends Spritesheet{

    private final int charWidth, charHeight;
    private String chars = "0123456789ABCDEF" +
                           "GHIJKLMNOPQRSTUV" +
                           "WXYZ????????????" +
                           "?????abcdefghijk" +
                           "lmnopqrstuvwxyz?" +
                           "????????????????" +
                           "#%&@$.,!?:;`\"()[" +
                           "]*/\\+-<=>???????" +
                           "????????█▓▒░?????";
        
    private Sprite[][] sprites;
    private int charactersWide, charactersHigh;
    
    public Font(BufferedImage image, int charWidth, int charHeight) {
       super(image);
        this.charWidth = charWidth;
        this.charHeight = charHeight;

        createSprites();
    }
    
    public Font(BufferedImage image, int charWidth, int charHeight, String chars){
        super(image);
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.chars = chars; 

        createSprites();
    }
    
    private void createSprites(){
        charactersWide = width / charWidth;
        charactersHigh = height / charHeight;
        
        sprites = new Sprite[charactersWide][charactersHigh];
        
        for(int y = 0; y < charactersHigh; y++){
            for(int x = 0; x < charactersWide; x++){
                sprites[x][y] = Sprite.cutFromSpritesheet(this, x, y, charWidth, charHeight);
            }
        }
    }
    
    public String getCharacters(){
        return chars;
    }
    
    public Sprite getCharacterSprite(int x, int y){
        if(x < 0 || y < 0 || x >= charactersWide || y >= charactersHigh) return null;
        return sprites[x][y];
    }
    
    public int getCharWidth(){
        return charWidth;
    }
    
    public int getCharHeight(){
        return charHeight;
    }
    
}
