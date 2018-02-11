package com.mac.spe;

import com.mac.spe.core.Engine;
import com.mac.spe.rendering.Renderer;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:25 PM.
 */
public class Main {
    
    public static void main(String[] args){
        int scale = 1;
        double fps = 60.0;
        Game game = new Game();
//        Engine engine = new Engine(game, "Pixel Engine", 240, 135, 8, 8, 1, fps);
//        Engine engine = new Engine(game, "Pixel Engine", 240, 135, scale, fps);
//        Engine engine = new Engine(game, "Pixel Engine", 480, 270, scale, fps);
//        Engine engine = new Engine(game, "Pixel Engine", 960, 540, scale, fps);
        Engine engine = new Engine(game, "Pixel Engine", 1920, 1080, scale, fps);
        engine.start();

        Renderer.transparentColor = 0xff000000;
        Renderer.ignoreColor = -1;
    }
}
