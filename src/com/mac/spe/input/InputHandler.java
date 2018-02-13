package com.mac.spe.input;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener{

    private boolean[] keysDown;
    private int mouseX, mouseY;
    private int mouseButton;

    public InputHandler(){
        this.keysDown = new boolean[65536];
    }

    public boolean isKeyDown(int keyCode){
        if(keyCode <=0 || keyCode >= keysDown.length) return false;
        return keysDown[keyCode];
    }

    public boolean isMouseButtonDown(int mouseButton){
        return mouseButton == mouseButton;
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

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        mouseButton = mouseEvent.getButton();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        mouseButton = mouseEvent.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        mouseButton = mouseEvent.getButton();
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getX();
        mouseY = mouseEvent.getY();
    }
}
