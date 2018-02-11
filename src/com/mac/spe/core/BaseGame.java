package com.mac.spe.core;

import com.mac.spe.rendering.Renderer;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:18 PM.
 */
public abstract class BaseGame {
    
    public abstract void init();
    public abstract boolean update();
    public abstract void render(Renderer renderer);
 
}
