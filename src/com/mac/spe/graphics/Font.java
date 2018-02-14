package com.mac.spe.graphics;

import java.awt.image.BufferedImage;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 08/02/2018 at 08:28 PM.
 */
public class Font extends Spritesheet{

    public static final byte ALIGN_LEFT = 0;
    public static final byte ALIGN_CENTER = 1;
    public static final byte ALIGN_RIGHT = 2;

    private final int charWidth, charHeight;
    private String chars = "0123456789ABCDEF" +
                           "GHIJKLMNOPQRSTUV" +
                           "WXYZ????????????" +
                           "?????abcdefghijk" +
                           "lmnopqrstuvwxyz?" +
                           "????????????????" +
                           "#%&@$.,!?:;'\"()[" +
                           "]*/\\+-<=>???????" +
                           "???⚪◔◑◕⚫█▓▒░???? ";
        
    private Sprite[][] sprites;
    private Sprite nullSprite;
    private int charactersWide, charactersHigh;

    public Font(BufferedImage image, int charWidth, int charHeight, char nullChar) {
        super(image);
        this.charWidth = charWidth;
        this.charHeight = charHeight;

        createSprites();
        if(chars.indexOf(nullChar) < 0) throw new IllegalArgumentException("'" + nullChar + "' is not a valid. Character must exist in chars String.");
        nullSprite = getCharacterSprite(nullChar);
    }
    
    public Font(BufferedImage image, int charWidth, int charHeight, Sprite nullSprite) {
        super(image);
        if(nullSprite == null) throw new IllegalArgumentException("Font must have a nullSprite.");
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.nullSprite = nullSprite;
        
        createSprites();
    }

    public Font(BufferedImage image, int charWidth, int charHeight, String chars, char nullChar){
        super(image);
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.chars = chars;
        
        createSprites();
        if(chars.indexOf(nullChar) < 0) throw new IllegalArgumentException("'" + nullChar + "' is not a valid. Character must exist in chars String.");
        nullSprite = getCharacterSprite(nullChar); 
    }
    
    public Font(BufferedImage image, int charWidth, int charHeight, String chars, Sprite nullSprite){
        super(image);
        if(nullSprite == null) throw new IllegalArgumentException("Font must have a nullSprite.");
        this.charWidth = charWidth;
        this.charHeight = charHeight;
        this.chars = chars;
        this.nullSprite = nullSprite;
        
        createSprites();
    }
    
    private void createSprites(){
        charactersWide = width / charWidth;
        charactersHigh = height / charHeight;
        
        sprites = new Sprite[charactersWide][charactersHigh];
        
        for(int y = 0; y < charactersHigh; y++){
            for(int x = 0; x < charactersWide; x++){
                sprites[x][y] = new Sprite(this, x, y, charWidth, charHeight);
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
    
    public Sprite getCharacterSprite(char c){
        int charIndex = chars.indexOf(c);
        if(charIndex < 0) return nullSprite;
        return getCharacterSprite(charIndex % 16, charIndex / 16);
    }
    
    public int getCharWidth(){
        return charWidth;
    }
    
    public int getCharHeight(){
        return charHeight;
    }
    
}
