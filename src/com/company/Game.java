package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel {

    private boolean running;
    private BufferedImage image;
    private Graphics2D g;
    private MyMouseMotionListener theMouseListener;
    private int mousex;
    private Ball theBall;
    private Paddle thePaddle;
    private Map theMap;
    private HUD theHud;
    private ArrayList<PowerUp> powerUps;


    public Game(){
    init();
    }

    public void init(){
        mousex = 0;
        theBall = new Ball();
        thePaddle = new Paddle();
        theMap = new Map(5, 8);
        theHud = new HUD();
        theMouseListener = new MyMouseMotionListener();
        powerUps = new ArrayList<PowerUp>();
        addMouseMotionListener(theMouseListener);
        running = true;
        image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    public void playGame(){
    while(running){
    update();
    draw();
    repaint();
    try{
        Thread.sleep(10);
    } catch (Exception e){
        e.printStackTrace();
    }
    }
    }
    public void checkCollisions(){
        Rectangle ballRect = theBall.getRect();
        Rectangle paddleRect = thePaddle.getRect();
        for(int i = 0; i < powerUps.size(); i++){
            Rectangle puRect = powerUps.get(i).getRect();
            if(paddleRect.intersects(puRect)){
                if(powerUps.get(i).getType() == PowerUp.WIDEPADDLE && !powerUps.get(i).getWasUsed()){
                    thePaddle.setWidth(thePaddle.getWidth() * 2);
                    powerUps.get(i).setWasUsed(true);
                }
            }
        }
        if(ballRect.intersects(paddleRect)){
            theBall.setDY(-theBall.getDY());
            if(theBall.getX() < mousex + thePaddle.getWidth() / 4){
                theBall.setDX(theBall.getDX() - .5);
            }
            if(theBall.getX() < mousex + thePaddle.getWidth() && theBall.getX() > mousex + thePaddle.getWidth() / 4){
                theBall.setDX(theBall.getDX() + .5);
            }
        }
        A: for(int row = 0; row < theMap.getMapArray().length; row++){
            for(int col = 0; col < theMap.getMapArray()[0].length; col++){
                if(theMap.getMapArray()[row][col] > 0){
                    int brickx = col * theMap.getBrickWidth() + theMap.HOR_PAD;
                    int bricky = row * theMap.getBrickHeight() + theMap.VERT_PAD;
                    int brickWidth = theMap.getBrickWidth();
                    int brickHeight = theMap.getBrickHeight();
                    Rectangle brickRect = new Rectangle(brickx, bricky, brickWidth, brickHeight);
                    if(ballRect.intersects(brickRect)){
                        if(theMap.getMapArray()[row][col] > 3){
                            powerUps.add(new PowerUp(brickx, bricky, theMap.getMapArray()[row][col], brickWidth, brickHeight));
                            theMap.setBrick(row, col, 3);
                        } else {
                            theMap.hitBrick(row, col);
                        }
                        theMap.hitBrick(row, col);
                        theBall.setDY(-theBall.getDY());
                        theHud.addScore(1);
                        break A;
                }

                }
            }
        }
    }
    public void update(){
        checkCollisions();
        theBall.update();
        thePaddle.update();
        for(PowerUp pu : powerUps){
            pu.update();
        }
    }
    public void draw(){
        //background
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
        //map
        theMap.draw(g);
        //ball
        theBall.draw(g);
        //paddle
        thePaddle.draw(g);
        //score
        theHud.draw(g);
        //power ups
        drawPowerUps();
        if(theMap.isWin() == true){
            drawWin();
            running = false;
        }
        if(theBall.isLose()){
            drawLoser();
            running = false;
        }

    }
    public void drawWin(){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("You win!", 230, 230);
    }
    public void drawPowerUps(){
        for(PowerUp pu : powerUps){
            pu.draw(g);
        }
    }
    public void drawLoser(){
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("You lose!", 230, 230);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(image, 0, 0, Main.WIDTH, Main.HEIGHT, null);
        g2.dispose();
    }
    private class MyMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e){

        }
        @Override
        public void mouseMoved(MouseEvent e){
            mousex = e.getX();
        thePaddle.mouseMoved(e.getX());
        }
    }
}
