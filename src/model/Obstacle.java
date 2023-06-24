/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * Obstacle. The square thing that go up
 */
public class Obstacle extends GameObject
{
    int score;
    boolean isStepped;
    String colorStr;
    /**
     * Constructor.
     */
    
    // Default constructor.
    public Obstacle()
    {
        super(0, 0, "Obstacle");
        super.setHeight(30);
        super.setWidth(500);
        score = 0;
        isStepped = false;
        colorStr = "fffffff";
    }
    
    // Constructor with obstacle position.
    public Obstacle(int x, int y)
    {
        super(x, y, "Obstacle");
        super.setHeight(30);
        super.setWidth(500);
        score = 0;
        isStepped = false;
        colorStr = "fffffff";
    }
    
    // Constructor with obstacle position and width.
    public Obstacle(int x, int y, int width)
    {
        super(x, y, "Obstacle");
        super.setHeight(30);
        super.setWidth(width);
        score = (550 - width)/5;
        isStepped = false;
        
        colorStr = "";
        switch (getWidth()) {
            case 50 -> colorStr = "#003366";
            case 100 -> colorStr = "#990000";
            case 150 -> colorStr = "#006600";
            case 200 -> colorStr = "#663300";
            case 250 -> colorStr = "#330066";
            case 300 -> colorStr = "#003333";
            case 350 -> colorStr = "#660066";
            case 400 -> colorStr = "#330000";
            case 450 -> colorStr = "#003300";
            case 500-> colorStr = "#333300";
            default -> colorStr = "#ffffff";
        }
    }
        
    @Override
    public void setScore(int score){
        this.score = score;
    }
    
    @Override
    public int getScore(){
        return this.score;
    }
    
    @Override
    public void setIsStepped(boolean isStepped){
        this.isStepped = isStepped;
    }
    
    @Override
    public boolean getIsStepped(){
        return this.isStepped;
    }
    
    /**
     * Override interface.
     */
    
    @Override
    public void render(Graphics object)
    {
        // Set player shape.
        object.setColor(Color.decode(colorStr));
        object.fillRect(x, y, getWidth(), getHeight());

        // Write its score
        object.drawString("" + this.score, this.width + 10, this.y+ 15);
    }
    
    @Override
    public void loop()
    {
        // Initialize velocity, so it can move.
        this.velY = -1.3;
        
        this.y += this.velY;
    }
}
