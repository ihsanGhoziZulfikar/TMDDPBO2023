/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import viewmodel.Game;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * Display to handle view of the game
 */
public class Display extends Canvas
{
    // JFrame declaration.
    private JFrame frame;
    
    /**
     * Constructor.
     */
    
    // Default constructor.
    public Display()
    {
        this.frame = new JFrame();
    }
    
    // Constructor with Frame data.
    public Display(int width, int height, String title)
    {
        // Initialize frame and its dimension.
        this.frame = new JFrame(title);
        this.frame.setPreferredSize(new Dimension(width, height));
        this.frame.setMinimumSize(new Dimension(width, height));
        this.frame.setMaximumSize(new Dimension(width, height));
        
        // Initialize additional options.
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLocationRelativeTo(null);
        
        this.frame.setResizable(false);
    }
    
    // Render background
    public void renderBg(Graphics g,String filename, int width, int height){
        Image bg = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/"+filename));
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(bg, 0, 0, width, height, null);
        g2d.dispose();
        g.drawImage(resizedImage, 0, 0, null);
    }
    
    // Render info (username, score, standing)
    public void renderInfo(Graphics g, String username, int score, int standing){
        Font oldFont = g.getFont();
        Font newFont = oldFont.deriveFont(oldFont.getSize() * 1.3f);
        g.setFont(newFont);

        g.setColor(Color.black);
        g.drawString("Username : " + username, 20, 30);
        g.drawString("Score : " + Integer.toString(score), 20, 50);
        g.drawString("Standing : " + Integer.toString(standing), 20, 70);
    }
    
    // Open / show game display. 
    public void open(Game game)
    {
        this.frame.add(game);
        this.frame.setVisible(true);
        
        game.start();
    }
    
    // Close game display.
    public void close()
    {
        this.frame.setVisible(false);
        this.frame.dispose();
    }
}
