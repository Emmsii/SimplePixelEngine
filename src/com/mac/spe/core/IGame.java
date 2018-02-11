package com.mac.spe.core;

import com.mac.spe.rendering.Renderer;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:18 PM.
 */
public interface IGame {
    
    void init();
    boolean update();
    void render(Renderer renderer);
 
}
