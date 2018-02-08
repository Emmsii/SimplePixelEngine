package com.mac.spe;

import com.mac.spe.core.Engine;
import com.mac.spe.core.Renderer;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:25 PM.
 */
public class Main {
    
    public static void main(String[] args){
        
        Game game = new Game();
        Engine engine = new Engine(game, "Pixel Engine", 640, 360, 2, 30.0);
        engine.start();

        Renderer.transparentColor = 0xff000000;
    }
}
