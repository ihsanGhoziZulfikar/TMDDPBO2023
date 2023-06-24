/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import model.GameObject;
import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import view.Display;
import view.Menu;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * Game handling
 */
public class Game extends Canvas implements Runnable
{
    /**
     * 
     * Attribute declaration.
     */
    
    /* View-related attributes. */
    public static final int width = 640;
    public static final int height = 480;
    private Display display;
    
    /* Process-related attributes. */
    private boolean running;
    private Handler handler;
    private Thread thread;
    
    /* Animation-related attributes. */
    private boolean startCounting = false;
    String username;
    private int score = 0;
    private int standing = 0;
    private int counter = 0;
    private int stateCounter = 0;
    private int direction = 0;
    
    private long lastObstacleTime = 0;
    Bgm bgm;
    Clip clip;
    
    
    // Default constructor.
    public Game()
    {
        try
        {
            
            // Initialize display.
            display = new Display(width, height, "Synchronization Tutorial");
            display.open(this); 
            
            // Initialize game handler.
            handler = new Handler();
            
            // Initialize controller (keyboard input).
            this.setFocusable(true);
            this.requestFocus();
            this.addKeyListener(new Controller(this, handler));
            
            // Initialize all object.
            running = true;
            if(running)
            {
                handler.addPlayer();
            }
        } catch(Exception e)
        {
            System.err.println("Failed to instance data.");
        }
    }
    
    /**
     * 
     * Getter and Setter.
     */
    
    /* Game running status. */
    
    public boolean isRunning()
    {
        return running;
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }
    /* Username */
    
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    
    /* Game score. */
    
    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }
    
    public void addScore(int score){
        this.score+=score;
    }
    
    /* Game standing */
    
    public int getStanding()
    {
        return this.standing;
    }

    public void setStanding(int standing)
    {
        this.standing = standing;
    }
    
    public void addStanding(){
        this.standing++;
    }
    
    /**
     * 
     * Public methods.
     */
    
    // Clamp, so player won't get off the display bound.
    public static int clamp(int var, int min, int max)
    {
        if(var >= max)
        {
            return var = max;
        }
        else if(var <= min)
        {
            return var = min;
        }
        
        return var;
    }

    // clamping game object
    public void clamp(GameObject object){
        int x = clamp(object.getX(), 0, (width - object.getWidth() - 20));
        int y = clamp(object.getY(), -10, (height - object.getHeight()));
        if(object.getX()!=x){
            object.setX(x);
            object.setVelX(0);
        }
        if(object.getY()!=y){
            endRun();
        }
    }
    
    // clamping obstacle
    public void clampObstacle(GameObject object){
        int y = clamp(object.getY(), -object.getHeight(), (height));
        if(object.getY()!=y){
            handler.remove(object);
        }
    }
    
    // Collision handler
    public int isCollided(GameObject obj1, GameObject obj2){
        if(obj1.getX()+obj1.getWidth() >= obj2.getX()){  //left
            if(obj1.getX() <= obj2.getX()+obj2.getWidth()){  //right
                if(obj1.getY()+obj1.getHeight() >= obj2.getY()){  //top
                    if(obj1.getY() <= obj2.getY()+obj2.getHeight()){  //bottom
                        double overlapX = Math.min(obj1.getX() + obj1.getWidth(), obj2.getX() + obj2.getWidth())
                            - Math.max(obj1.getX(), obj2.getX());
                        double overlapY = Math.min(obj1.getY() + obj1.getHeight(), obj2.getY() + obj2.getHeight())
                            - Math.max(obj1.getY(), obj2.getY());

                        if (overlapX > overlapY) {
                            if (obj1.getY() < obj2.getY()) {
                                return 1;       // top
                            } else {
                                return 2;       //bottom
                            }
                        } else {
                            if (obj1.getX() < obj2.getX()) {
                                return 3;       //left
                            } else {
                                return 4;       //right
                            }
                        }
                    }
                }
            }
        }
        return 0;
    }
    
    // on game over
    public void endRun(){
        UserProcess userProcess = new UserProcess();
        userProcess.updateScore(username,score,standing);
        userProcess.close();
        
        JOptionPane.showMessageDialog(null, "Game Over\nScore: "+score+"\nStanding: "+standing);
            
        bgm.stopSound(this.clip);
        this.close();
        Menu menu = new Menu();
        menu.setVisible(true);
        
    }
    // Close display.
    public void close()
    {
        running = false;
        display.close();
    }
    
    /**
     * 
     * Game controller.
     */
    
    // Start threading.
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start(); running = true;
        
    }
    
    // Stop threading.
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        }
        catch(InterruptedException e)
        {
            System.out.println("Thread error : " + e.getMessage());
        }
    }
    
    // Initialize game when it run for the first time.
    public void render()
    {
        // Use buffer strategy.
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(SOMEBITS);
            return;
        }
        
        // Initialize graphics.
        Graphics g = bs.getDrawGraphics();
        
        if(running == true)
        {
            // render background
            display.renderBg(g,"game.jpg",width,height);
            
            // Render handler.
            handler.render(g);
            
            // Render score.
            display.renderInfo(g,username, score, standing);
            
        }
        
        // Loop the process so it seems like "FPS".
        g.dispose();
        bs.show();
    }
    
    // Main loop proccess.
    public void loop()
    {
        GameObject player = null;
        GameObject obstacle = null;
        
        handler.loop();
        if(this.running)
        {   
            counter++;
            if(startCounting)
            {
                stateCounter++;
            }
            
            if(stateCounter >= 40)
            {
                stateCounter = 0;
                startCounting = false;
            }
            
            if(counter >= 50)
            {
                direction = (direction == 0) ? 1 : 0;
                counter = 0;
            }
            
            // spawn obstacle every 0.6 second
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastObstacleTime >= 600) { 
                lastObstacleTime = currentTime;
                handler.addObstacle();
            }
            System.out.println(handler.count());
            
            // search for player in handler
            for(int i = 0; i < handler.count(); i++)
            {
                if(handler.get(i).getType().equals("Player")){
                    player = handler.get(i);
                    clamp(player);
                    break;
                }
            }
            // search for obstacle in handler, then handle collision with player
            for(int i = 0; i < handler.count(); i++)
            {
                if(handler.get(i).getType().equals("Obstacle")){
                    obstacle = handler.get(i);
                    clampObstacle(obstacle);
                    
                    if(player!=null){
                        // handle collision based on which direction the player hit obstacle
                        
                        int collision = this.isCollided(player,handler.get(i));
                        switch(collision){
                            case 0:
                                break;
                            case 1:
                                player.setY(obstacle.getY()-player.getHeight());
                                
                                player.setIsStanding(true);
                                player.setIsBumped(false);
                                
                                if(handler.get(i).getIsStepped()==false){
                                    this.addScore(handler.get(i).getScore());
                                    this.addStanding();
                                    handler.get(i).setIsStepped(true);
                                }
                                break;
                            case 2:
                                player.setY(obstacle.getY()+obstacle.getHeight());
                                
                                player.setIsStanding(false);
                                player.setIsBumped(true);
                                break;
                            case 4:
                                player.setX(obstacle.getX()+obstacle.getWidth());
                                
                                player.setIsStanding(false);
                                player.setIsBumped(false);
                                break;
                            default:
                                player.setIsStanding(false);
                                player.setIsBumped(false);
                                break;
                        }
                    }
                }
            }
        }
    }
    
    /**
     * 
     * Override interface.
     */
    
    @Override
    public void run()
    {
        double fps = 60.0;
        double ns = (1000000000 / fps);
        double delta = 0;
        
        // Timer attributes.
        long time = System.nanoTime();
        long now = 0;
        long timer = System.currentTimeMillis();
        
        int frames = 0;
        
        bgm = new Bgm();
        clip = bgm.playSound(this.clip, "Financial-Obligations-PM-Music.wav");
        
        while(running)
        {
            now = System.nanoTime();
            delta += ((now - time) / ns);
            time = now;
            
            while(delta > 1)
            {
                loop();
                delta--;
            }
            
            if(running)
            {
                render();
                frames++;
            }
            
            if((System.currentTimeMillis() - timer) > 1000)
            {
                timer += 1000;
                frames = 0;
            }
            
        }
        
        stop();
    }
}
