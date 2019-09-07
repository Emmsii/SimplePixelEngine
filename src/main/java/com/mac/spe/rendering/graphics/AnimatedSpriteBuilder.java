package com.mac.spe.rendering.graphics;

import java.util.ArrayList;
import java.util.List;

public class AnimatedSpriteBuilder {

    private final Spritesheet spritesheet;
    private final int frameTime;

    private List<Sprite> frames = new ArrayList<>();

    public AnimatedSpriteBuilder(Spritesheet spritesheet, int frameTime){
        this.spritesheet = spritesheet;
        this.frameTime = frameTime;
    }

    public AnimatedSprite build(){
        Sprite[] framesArray = new Sprite[frames.size()];
        return new AnimatedSprite(frames.toArray(framesArray), frameTime);
    }

    public AnimatedSpriteBuilder addFrame(int x, int y, int width, int height){
        frames.add(new Sprite(spritesheet, x, y, width, height));
        return this;
    }
}
