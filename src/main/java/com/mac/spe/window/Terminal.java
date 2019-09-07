package com.mac.spe.window;

import javax.swing.*;

/**
 * Project: SimplePixelEngine
 * PC
 * Created by Matt on 08/02/2018 at 08:07 PM.
 */
public class Terminal extends JFrame{
    
    public Terminal(String title, Panel panel){
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(panel);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
