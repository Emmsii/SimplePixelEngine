package com.mac.spe;

import com.mac.spe.graphics.Renderer;
import com.mac.spe.window.Panel;
import com.mac.spe.window.Terminal;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 07/02/2018 at 06:20 PM.
 */
public class Engine implements Runnable{
    
    private final BaseGame game;
    
    private Thread thread;
    private boolean running;

    private String title;
    private final int width, height;
    private final int scale;
    private double targetFps = 30.0;
    
    private Renderer renderer;
    private Panel panel;
    private Terminal terminal;
    
    public Engine(BaseGame game, String title, int width, int height, int scale, double targetFps){
        if(game == null) throw new IllegalArgumentException("Cannot create a new Engine instance with a null game.");
        if(targetFps <= 0) throw new IllegalArgumentException("Target FPS must be greater than 0.");
        
        if(width <= 0) throw new IllegalArgumentException("Engine width must be greater than 0.");
        if(height <= 0) throw new IllegalArgumentException("Engine height must be greater than 0.");
        if(scale <= 0) throw new IllegalArgumentException("Engine scale must be greater than 0.");
        
        this.game = game;
        this.title = title;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.targetFps = targetFps;
        this.running = false;
        
        start();
    }
    
    private void init(){
        renderer = new Renderer(width * scale, height * scale);
        panel = new Panel(width, height, scale, renderer);
        terminal = new Terminal(title, panel);
        
        game.init();
    }
    
    private boolean update(){
        return game.update();
    }
    
    private void render(){
        renderer.clear(0xff00ff);
        game.render(renderer);
        terminal.repaint();
    }
    
    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this, "pixel_engine");
        thread.start();
    }

    public synchronized void stop(){
        if(!running || thread == null) return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
//        init();
//
//        double nsPerFrame = 1000000000.0 / targetFps;
//        double unprocessedTime = 0;
//        double maxSkipFrames = 10;
//
//        long lastTime = System.nanoTime();
//        long lastFrameTime = System.currentTimeMillis();
//        int frames = 0;
//        boolean shouldRender;
//
//        while(running){
//            long now = System.nanoTime();
//            double passedTime = (now - lastTime) / nsPerFrame;
//            lastTime = now;
//
//            if(passedTime < -maxSkipFrames) passedTime = -maxSkipFrames;
//            if(passedTime < maxSkipFrames) passedTime = maxSkipFrames;
//
//            unprocessedTime += passedTime;
//            
//            while(unprocessedTime > 1){
//                unprocessedTime--;
//                shouldRender = update();
//
//                if(shouldRender){
//                    render();
//                    frames++;
//                }
//
//                if(System.currentTimeMillis() > lastFrameTime + 1000){
//                    System.out.println(frames + "fps");
//                    lastFrameTime += 1000;
//                    frames = 0;
//                }
//            }
//        }

        long lastTime = System.nanoTime();
        double unprocessed = 0;
        double nsPerTick = 1000000000.0 / 60;
        int frames = 0;
        int ticks = 0;
        long lastTimer1 = System.currentTimeMillis();

        init();

        while (running) {
            long now = System.nanoTime();
            unprocessed += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            while (unprocessed >= 1) {
                ticks++;
                shouldRender = update();
                unprocessed -= 1;
            }

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (shouldRender) {
                frames++;
                render();
            }

            if (System.currentTimeMillis() - lastTimer1 > 1000) {
                lastTimer1 += 1000;
                System.out.println(ticks + " ticks, " + frames + " fps");
                frames = 0;
                ticks = 0;
            }
        }
    
        
        stop();
    }

    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }
}
