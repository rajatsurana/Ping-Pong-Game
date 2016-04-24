package com.game.saurabh;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Movepaddle extends JFrame {
	
	
public Movepaddle() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Board());
        
        setSize(400, 300);
        setResizable(false);
        
        setTitle("Moving paddle");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                Movepaddle ex = new Movepaddle();
                ex.setVisible(true);
            }
        });
    }
}
