package com.mac.spe;

import com.mac.spe.core.BaseGame;
import com.mac.spe.rendering.RenderMode;
import com.mac.spe.rendering.Renderer;
import com.mac.spe.graphics.*;
import com.mac.spe.io.ImageLoader;

import java.util.Random;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:26 PM.
 */
public class Game extends BaseGame {
    
    private Sprite wall1;
    private Sprite wall2;
    private Sprite wall3;

    int[][] tiles = new int[10][10];
    
    Font font;
    
    @Override
    public void init() {
        
        Renderer.setRenderMode(RenderMode.PRECISE);
        
        font = new Font(ImageLoader.load("res/font2.png"), 8, 16, '?');
        
        Spritesheet sheet = new Spritesheet(ImageLoader.load("res/tiles.png"));
        wall1 = Sprite.cutFromSpritesheet(sheet, 0, 0, 16, 16);
        wall2 = Sprite.cutFromSpritesheet(sheet, 0, 2, 16, 16);
        wall3 = Sprite.cutFromSpritesheet(sheet, 2, 2, 16, 16);

        for(int y = 0; y < 10; y++) {
            for (int x = 0; x < 10; x++) {
                tiles[x][y] = random.nextInt(3);
            }
        }
    }

    @Override
    public boolean update() {
        return true;
    }

    Random random = new Random();
    
    @Override
    public void render(Renderer renderer) {
        renderer.clear(0xff00ff);

//        renderer.drawSpriteColored(wall1, 20, 20, 0x4286f4);
        
//        for(int y = 0; y < 10; y++){
//            for(int x = 0; x < 10; x++){
//                switch (tiles[x][y]){
//                    case 0:
//                        renderer.drawSprite(wall1, x * 16, y * 16);
//                        break;
//                    case 1:
//                        renderer.drawSprite(wall2, x * 16, y * 16);
//                        break;
//                    case 2:
//                        renderer.drawSprite(wall3, x * 16, y * 16);
//                        break;
//                }
//                        
//            }
//        }
        
        renderer.write("It's dangerous to go alone! Take this.", font, 5, 5, -1, -1);
        
    }
}
