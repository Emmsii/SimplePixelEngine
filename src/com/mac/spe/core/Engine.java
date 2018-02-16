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

    public Engine(IGame game, String title, int widthInTiles, int heightInTiles, int tileWidth, int tileHeight, int scale, double targetFps, boolean uncappedFrameRate){
        this(game, title, widthInTiles * tileWidth, heightInTiles * tileHeight, scale, targetFps, uncappedFrameRate);
    }
    
    public Engine(IGame game, String title, int width, int height, int scale, double targetFps, boolean uncappedFrameRate){
        if(game == null) throw new IllegalArgumentException("Cannot create a new Engine with a null game.");
        if(targetFps <= 0) throw new IllegalArgumentException("Target FPS must be greater than 0.");

        if(width <= 0) throw new IllegalArgumentException("Engine width must be greater than 0.");
        if(height <= 0) throw new IllegalArgumentException("Engine height must be greater than 0.");
        if(scale <= 0) throw new IllegalArgumentException("Engine scale must be greater than 0.");

        this.game = game;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.title = title;
        this.targetFps = targetFps;
        this.uncappedFrameRate = uncappedFrameRate;
        
        renderer = new Renderer(width, height);
        panel = new Panel(width, height, scale, renderer);
        terminal = new Terminal(title + " | 0fps", panel);

        inputHandler = new InputHandler();
        input = new Input(inputHandler);

        panel.addKeyListener(inputHandler);
        panel.addMouseListener(inputHandler);
        panel.addMouseMotionListener(inputHandler);
    }

    public void init(){
        game.init();
    }
    
    public boolean update(){
        return game.update(input);
    }
    
    public void render(){
        game.render(renderer);
        panel.render();
    }
    
    public synchronized void start(){
        if(running) return;
        running = true;
        thread = new Thread(this, "pixe-engine");
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

            if (shouldRender || uncappedFrameRate) {
                render();
                frames++;
            }

            if (System.currentTimeMillis() > lastFrameTime + 1000) {
                System.out.println(frames + "fps " + updates + "ups");
                terminal.setTitle(title + " | " + frames + "fps " + updates + "ups");
                lastFrameTime += 1000;
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
