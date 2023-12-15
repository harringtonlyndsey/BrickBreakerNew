package com.company;

import java.awt.*;

public class HUD {
    private int score;

    public HUD(){
        init();
    }
    public void init(){
        score = 0;
    }
    public void draw(Graphics2D g){
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 20, 20);
    }
    public int getScore() { return score; }
    public void addScore(int scoreToAdd){
        score += scoreToAdd;
    }
}
