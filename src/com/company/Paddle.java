package com.company;

import java.awt.*;

public class Paddle {
    private double x;
    private int width, height, startWidth;
    private long widthTimer;
    private boolean altWidth;

    public final int YPOS = Main.HEIGHT - 100;

    public Paddle(){
        altWidth = false;
        width = 80;
        startWidth = 80;
        height = 15;
        x = Main.WIDTH / 2- width / 2;

    }
    public void update(){
        if((System.nanoTime() - widthTimer) / 1000 > 4000000){
            width = startWidth;
            altWidth = false;
        }
    }
    public void draw(Graphics2D g){
        g.setColor(Color.PINK);
        g.fillRect((int) x, YPOS, width, height);
        if(altWidth == true){
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.drawString("Shrinking in " + (4 - (System.nanoTime() - widthTimer) / 1000000000), (int)x, YPOS + 15);
        }
    }
    public void mouseMoved(int mouseXPos){
        x = mouseXPos;
        if(x > Main.WIDTH - width){
            x = Main.WIDTH - width;
        }
    }
    public Rectangle getRect(){
        return new Rectangle((int)x, YPOS, width, height);
    }
    public int getWidth(){ return width; }
    public void setWidth(int newWidth){
        altWidth = true;
        width = newWidth;
        setWidthTimer();
    }
    public void setWidthTimer(){
        widthTimer = System.nanoTime();
    }
}
