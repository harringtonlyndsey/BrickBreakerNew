package com.company;

import java.awt.*;

public class Ball {
    private double x, y, dx, dy;
    private int ballSize = 15;

    public Ball(){
        x = 200;
        y = 200;
        dx = 1;
        dy = 3;
    }

    public void update(){
        setPosition();
    }
    public void setPosition(){
        x += dx;
        y += dy;
        if(x < 0){
            dx = -dx;
        }
        if(y < 0){
            dy = -dy;
        }
        if(x > Main.WIDTH - ballSize){
            dx = -dx;
        }
        if(y > Main.HEIGHT - ballSize){
          dy = -dy;
        }
    }
    public void draw(Graphics2D g){
        g.setColor(Color.PINK);
        g.setStroke(new BasicStroke(4));
        g.drawOval((int)x, (int)y, ballSize, ballSize);
    }
    public Rectangle getRect(){
        return new Rectangle((int)x, (int)y, ballSize, ballSize);
    }
    public void setDY(double theDY){ dy = theDY; }
    public double getDY(){ return dy; }
    public void setDX(double theDX){ dx = theDX; }
    public double getDX() { return dx; }
    public double getX(){ return x; }
    public boolean isLose(){
        boolean loser = false;
        if(y > Main.HEIGHT - ballSize * 2){
            loser = true;
        }
        return loser;
    }
}
