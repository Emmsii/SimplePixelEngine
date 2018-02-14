package com.mac.spe.graphics;

public class AnimatedSprite extends Sprite{

    private final Sprite[] frames;
    private final int frameTime;

    private int currentTick = 0;
    private int currentFrame = 0;

    public AnimatedSprite(Sprite[] frames, int frameTime) {
        super(null, -1, -1, -1, -1);
        this.frames = frames;
        this.frameTime = frameTime;
    }

    public void update(){
        if(currentTick++ % frameTime == 0) {
            currentFrame = (currentFrame + 1) % frames.length;
        }
    }

    @Override
    public int getPixel(int xp, int yp) {
        return frames[currentFrame].getPixel(xp, yp);
    }

    @Override
    public int getWidth() {
        return frames[currentFrame].getWidth();
    }

    @Override
    public int getHeight() {
        return frames[currentFrame].getHeight();
    }
}
