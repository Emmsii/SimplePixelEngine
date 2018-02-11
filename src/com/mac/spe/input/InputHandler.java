package com.mac.spe.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener{

    private boolean[] keysDown;

    public InputHandler(){
        this.keysDown = new boolean[65536];
    }

    public boolean isKeyDown(int keyCode){
        if(keyCode <=0 || keyCode >= keysDown.length) return false;
        return keysDown[keyCode];
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() > 0 && keyEvent.getKeyCode() < keysDown.length) keysDown[keyEvent.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() > 0 && keyEvent.getKeyCode() < keysDown.length) keysDown[keyEvent.getKeyCode()] = false;
    }
}
