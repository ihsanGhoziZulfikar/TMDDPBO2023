/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * Player. The ball that get played around
 */
public class Player extends GameObject
{
    private boolean isStanding;
    private boolean isBumped;
    /**
     * Constructor.
     */
    
    // Default constructor.
    public Player()
    {
        super(0, 0, "Player");
        super.setHeight(30);
        super.setWidth(30);
        this.isStanding = false;
        this.isBumped = false;
    }
    
    // Constructor with player position.
    public Player(int x, int y)
    {
        super(x, y, "Player");
        super.setHeight(30);
        super.setWidth(30);
        this.isStanding = false;
        this.isBumped = false;
    }
    
    @Override
    public void setIsStanding(boolean isStanding){
        this.isStanding = isStanding;
    }
    
    @Override
    public boolean getIsStanding(){
        return this.isStanding;
    }
    
    @Override
    public void setIsBumped(boolean isBumped){
        this.isBumped = isBumped;
    }
    
    @Override
    public boolean getIsBumped(){
        return this.isBumped;
    }
    
    /* Jump */
    
    @Override
    public void jump(){
        this.isStanding = false;
        Thread jumpThread = new Thread(() -> {
            double i = -10.5;     // velocity when player is jumping
            int counter = 0;    // i controller
            while (i<0) {
                this.velY = i;
                counter++;
                if (counter % 10 == 0 && i < 0) {
                    i+=0.5;
                }
                if(i==0){
                    this.velY=0.5;
                    break;
                }
                if(isBumped){
                    this.velY=0.5;
                    break;
                }

                try {
                    Thread.sleep(1); // a small delay 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        jumpThread.start();
    }
    /**
     * Override interface.
     */
    
    @Override
    public void render(Graphics object)
    {
        // Use image
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/ball.png"));
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(bg, 0, 0, width, height, null);
        g2d.dispose();
        object.drawImage(resizedImage, x, y, null);
    }
    
    @Override
    public void loop()
    {
        // Initialize velocity, so it can move.
        if(this.isStanding==false){
            this.velY+=0.1;
        }else{
            this.velY=1;
        }
        
        this.x += this.velX;
        this.y += this.velY;
        
    }
}
