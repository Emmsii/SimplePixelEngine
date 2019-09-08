package com.mac.spe.input;

public class Input {

    private InputHandler inputHandler;
    private boolean hasMouseMoved = false;
    
    private int currentMouseX, currentMouseY;
    private int lastMouseX, lastMouseY;

    public Input(InputHandler inputHandler){
        this.inputHandler = inputHandler;
    }

    public void update(){
        currentMouseX = getMouseX();
        currentMouseY = getMouseY();
        
        hasMouseMoved = currentMouseX != lastMouseX || currentMouseY != lastMouseY;
        
        lastMouseX = currentMouseX;
        lastMouseY = currentMouseY;
    }
    
    public boolean isKeyDown(int keyCode){
        return inputHandler.isKeyDown(keyCode);
    }

    public boolean isMouseButtonDown(int mouseButton){
        return inputHandler.isMouseButtonDown(mouseButton);
    }

    public boolean mouseHasFocus(){
        return inputHandler.mouseHasFocus();
    }
    
    public boolean hasMouseMoved(){
        return hasMouseMoved;
    }

    public int getScaledMouseX(){
        return inputHandler.getScaledMouseX();
    }

    public int getScaledMouseY(){
        return inputHandler.getScaledMouseY();
    }

    public int getMouseX(){
        return inputHandler.getMouseX();
    }

    public int getMouseY(){
        return inputHandler.getMouseY();
    }
}
