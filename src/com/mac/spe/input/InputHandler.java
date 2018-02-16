package com.mac.spe.input;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener{

    private boolean[] keysDown = new boolean[65536];
    private boolean[] mouseButtonsDown = new boolean[3];
    private int mouseX, mouseY;
    private boolean mouseHasFocus = false;

    private final int windowScale;
    
    public InputHandler(int windowScale){
        if(windowScale <= 0) throw new IllegalArgumentException("Window Scale must be greater than 0.");
        this.windowScale = windowScale;
    }

    public boolean isKeyDown(int keyCode){
        if(keyCode <=0 || keyCode >= keysDown.length) return false;
        return keysDown[keyCode];
    }

    public boolean isMouseButtonDown(int mouseButton){
        if(mouseButton >= 0 && mouseButton < mouseButtonsDown.length) return mouseButtonsDown[mouseButton];
        return false;
    }
    
    public boolean mouseHasFocus(){
        return mouseHasFocus;
    }

    public int getScaledMouseX(){
        return mouseX == 0 ? 0 : mouseX / windowScale;
    }
    
    public int getScaledMouseY(){
        return mouseY == 0 ? 0 : mouseY / windowScale;
    }
    
    public int getMouseX(){
        return mouseX;
    }
    
    public int getMouseY(){
        return mouseY;
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
        
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() >= 0 && mouseEvent.getButton() < mouseButtonsDown.length) mouseButtonsDown[mouseEvent.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() >= 0 && mouseEvent.getButton() < mouseButtonsDown.length) mouseButtonsDown[mouseEvent.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
        mouseHasFocus = true;
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
        mouseHasFocus = false;
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
