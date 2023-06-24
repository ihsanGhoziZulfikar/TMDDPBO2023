/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.awt.Graphics;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * abstract class for all of game's object
 */
public abstract class GameObject implements GameInterface
{
    /**
     * Attribute declaration.
     */
    
    protected int x, y;             // Position.
    protected int width, height;    // Dimension.
    protected double velX, velY;    // Velocity.
    protected String type;          // Object type.
    
    /**
     * Constructor.
     */
    
    // Default constructor.
    public GameObject()
    {
        this.x = 0;
        this.y = 0;
        this.type = "";
    }
    
    // Constructor with object coordinate.
    public GameObject(int x, int y, String type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
    // Constructor with object coordinate and shape.
    public GameObject(int x, int y, int width, int height, String type)
    {
        this.x = x; this.y = y;
        this.width = width; this.height = height;
        this.type = type;
    }
    
    /**
     * Getter and Setter.
     */

    /* Object X position. */
    
    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    /* Object Y position. */
    
    public int getY()
    {
        return y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    /* Object width. */
    
    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
    
    /* Object height. */
    
    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
    
    /* Object X velocity. */
    
    public double getVelX()
    {
        return velX;
    }

    public void setVelX(int velX)
    {
        this.velX = velX;
    }

    /* Object Y velocity. */
    
    public double getVelY()
    {
        return velY;
    }

    public void setVelY(int velY)
    {
        this.velY = velY;
    }
    
    /* Object type. */
    
    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
    
    /* Wehter the object has been stepped on */
    
    public void setIsStepped(boolean isStepped){
    }
    public boolean getIsStepped(){
        return false;
    }
    
    /* Wheter the object is standing on something */
    
    public void setIsStanding(boolean isStanding){
    }
    
    public boolean getIsStanding(){
        return false;
    }
    
    /* Jump */
    
    public void jump(){
    }
    
    /* Wheter the object bumped on to something */
    
    public void setIsBumped(boolean isBumped){
    }
    public boolean getIsBumped(){
        return false;
    }
    public void setScore(int score){
    }
    public int getScore(){
        return 0;
    }
    
    /**
     * Override interface (unused, only to avoid error).
     */
    
    @Override
    public void render(Graphics object)
    {
        
    }
    
    @Override
    public void loop()
    {
        
    }
}
