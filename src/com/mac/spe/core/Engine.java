package com.mac.spe.core;

import com.mac.spe.input.Input;
import com.mac.spe.input.InputHandler;
import com.mac.spe.rendering.Renderer;
import com.mac.spe.window.Panel;
import com.mac.spe.window.Terminal;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 08/02/2018 at 05:46 PM.
 */
public class Engine implements Runnable{
    
    private final IGame game;

    private final int width, height;
    private final int scale;
    private final String title;

    private Renderer renderer;
    private Panel panel;
    private Terminal terminal;
    
    private Thread thread;
    private boolean running = false;
    private final double targetFps;
    private final boolean uncappedFrameRate;

    private Input input;
    private InputHandler inputHandler;
    
    private boolean printFpsStats = false;
    private int fps, ups;

    public Engine(EngineConfig config, IGame game){
        if(config == null) throw new IllegalArgumentException("Cannot create a new engine with a null config.");
        if(game == null) throw new IllegalArgumentException("Cannot create a new engine with a null game.");
        this.game = game;
        this.width = config.getWidth();
        this.height = config.getHeight();
        this.scale = config.getScale();
        this.title = config.getTitle();
        this.targetFps = config.getTargetFramesPerSecond();
        this.uncappedFrameRate = config.isUncappedFrameRate();
                
        if(uncappedFrameRate) System.err.println("Warning! Uncapped frame rate will ignore the return value of the update method.");
        
        renderer = new Renderer(width, height);
        panel = new Panel(width, height, scale, renderer);
        terminal = new Terminal(title, panel);

        inputHandler = new InputHandler(3, scale);
        input = new Input(inputHandler);

        panel.addKeyListener(inputHandler);
        panel.addMouseListener(inputHandler);
        panel.addMouseMotionListener(inputHandler);
    }

    public void init(){
        game.init();
        render(); //Render a single frame to start.
    }
    
    public boolean update(){
        input.update();
        return game.update(input);
    }
    
    public void render(){
        game.render(renderer);
        panel.render();
    }
    
    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this, "pixel-engine");
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
        init();

        double nsPerFrame = 1000000000.0 / targetFps;
        double unprocessedTime = 0;
        double maxSkipFrames = 10;

        long lastTime = System.nanoTime();
        long lastFrameTime = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;

        while (running) {
            long now = System.nanoTime();
            double passedTime = (now - lastTime) / nsPerFrame;
            lastTime = now;

            if (passedTime < -maxSkipFrames) passedTime = -maxSkipFrames;
            if (passedTime > maxSkipFrames) passedTime = maxSkipFrames;

            unprocessedTime += passedTime;

            boolean shouldRender = false;
            while (unprocessedTime > 1) {
                unprocessedTime -= 1;
                shouldRender = update();
                updates++;
            }

            //If uncapped, render all the time.
            if (shouldRender || uncappedFrameRate) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() > lastFrameTime + 1000) {
                if(printFpsStats) System.out.println(frames + "fps " + updates + "ups");
                lastFrameTime += 1000;
                fps = frames;
                ups = updates;
                frames = 0;
                updates = 0;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void setPrintFpsStats(boolean printFpsStats){
        this.printFpsStats = printFpsStats;
    }
    
    public int getFps(){
        return fps;
    }
    
    public int getUps(){
        return ups;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getScale(){
        return scale;
    }

}
