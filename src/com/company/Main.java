package com.company;

import javax.swing.*;


public class Main {

    public static final int WIDTH = 640, HEIGHT = 480;

    public static void main(String[] args){
        JFrame theFrame = new JFrame("Brick Breaker");
        //theFrame.setLocationRelativeTo(null);
        theFrame.setResizable(false);
        theFrame.setSize(WIDTH, HEIGHT);
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
        Game thePanel = new Game();
        theFrame.add(thePanel);
        thePanel.playGame();

    }
}
