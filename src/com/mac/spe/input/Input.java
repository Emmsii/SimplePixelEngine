package com.mac.spe.input;

public class Input {

    private InputHandler inputHandler;

    public Input(InputHandler inputHandler){
        this.inputHandler = inputHandler;
    }

    public boolean isKeyDown(int keyCode){
        return inputHandler.isKeyDown(keyCode);
    }

    public boolean isMouseButtonDown(int mouseButton){
        return inputHandler.isMouseButtonDown(mouseButton);
    }
}
