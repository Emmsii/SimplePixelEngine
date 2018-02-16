package com.mac.spe;

import com.mac.spe.core.IGame;
import com.mac.spe.graphics.*;
import com.mac.spe.helpers.StringHelper;
import com.mac.spe.input.Input;
import com.mac.spe.io.ImageLoader;
import com.mac.spe.rendering.RenderMode;
import com.mac.spe.rendering.Renderer;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:26 PM.
 */
public class Game implements IGame {
    
    private Sprite wall1;
    private Sprite wall2;
    private Sprite wall3;

    private AnimatedSprite anSprite;

    int[][] tiles = new int[240][135];
    
    Font font;
    
    @Override
    public void init() {

        String input = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. A arcu cursus vitae congue mauris rhoncus. Pretium vulputate sapien nec sagittis aliquam malesuada bibendum arcu. Aliquam etiam erat velit scelerisque in dictum. In arcu cursus euismod quis viverra nibh cras pulvinar. Viverra vitae congue eu consequat ac felis donec et odio. Metus aliquam eleifend mi in. Pellentesque massa placerat duis ultricies lacus. Molestie at elementum eu facilisis sed odio morbi quis. Erat imperdiet sed euismod nisi. Dictum non consectetur a erat nam at lectus urna. Est sit amet facilisis magna etiam.";
        List<String> lines = StringHelper.stringToMultiline(input, 50, false);

        for(String s : lines) System.out.println(s);

        System.exit(0);

        font = new Font(ImageLoader.load("res/font.png"), 8, 16, '?');
        
        Spritesheet sheet = new Spritesheet(ImageLoader.load("res/tiles.png"));
//        wall1 = Sprite.cutFromSpritesheet(sheet, 0, 0, 16, 16).overrideColors(0x00ff00, -1);
//        wall2 = Sprite.cutFromSpritesheet(sheet, 0, 2, 16, 16).overrideColors(-1, -1);
//        wall3 = Sprite.cutFromSpritesheet(sheet, 2, 2, 16, 16).overrideColors(-1, -1);
        wall1 = new Sprite(sheet, 0, 0, 16, 16);
        wall2 = new Sprite(sheet, 0, 2, 16, 16);
        wall3 = new Sprite(sheet, 2, 2, 16, 16);

        anSprite = new AnimatedSpriteBuilder(font, 5)
                .addFrame(3, 8, 8, 16)
                .addFrame(4, 8, 8, 16)
                .addFrame(5, 8, 8, 16)
                .addFrame(6, 8, 8, 16)
                .addFrame(7, 8, 8, 16)
                .build();

        for(int y = 0; y < 135; y++) {
            for (int x = 0; x < 240; x++) {
                tiles[x][y] = random.nextInt(3);
            }
        }
    }

    @Override
    public boolean update(Input input) {

        if(input.isKeyDown(KeyEvent.VK_W)){
            t += 0.02f;
        }


        anSprite.update();
        return true;
    }

    Random random = new Random();
    
    float t;
    
    @Override
    public void render(Renderer renderer) {
        renderer.clear(0xd8ae65);

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
        
        renderer.setRenderMode(RenderMode.TILED);
//        for(int y = 0; y < 100; y++) {
//            for (int x = 0; x < 100; x++) {
//                switch (tiles[x][y]) {
//                    case 0:
//                        renderer.drawSprite(wall1, x, y);
//                        break;
//                    case 1:
//                        renderer.drawSprite(wall2, x, y);
//                        break;
//                    case 2:
//                        renderer.drawSprite(wall3, x, y);
//                        break;
//                }
//            }
//        }

        renderer.drawSpriteColored(anSprite, 0, 0, 0xff00ff);
        renderer.drawSpriteColored(anSprite, 1, 1, 0x0000ff);
        renderer.drawSpriteColored(anSprite, 2, 2, 0xff0000);
        renderer.drawSpriteColored(anSprite, 3, 3, 0x00ff00);

        renderer.setRenderMode(RenderMode.PRECISE);

        int x = (int) (Math.sin(t) * 100);
        int y = (int) (Math.cos(t) * 100);

        renderer.write("It's dangerous to go alone! Take this.", font, x, y, 0x0000ff, -1, Font.ALIGN_RIGHT);
    }
}
