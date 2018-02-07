package com.mac.spe;

import com.mac.spe.graphics.Renderer;
import com.mac.spe.graphics.Sprite;
import com.mac.spe.graphics.Spritesheet;
import com.mac.spe.io.ImageLoader;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:26 PM.
 */
public class Game extends BaseGame{
    
    private Sprite test;
    
    @Override
    public void init() {
        Spritesheet sheet = new Spritesheet(ImageLoader.load("res/tiles.png"));
        test = Sprite.cutFromSpritesheet(sheet, 0, 0, 8, 8);
    }

    @Override
    public boolean update() {
        return true;
    }

    @Override
    public void render(Renderer renderer) {
        renderer.drawSprite(test, 20, 20);
    }
}
