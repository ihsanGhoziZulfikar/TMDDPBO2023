/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model;

import java.awt.Graphics;

/**
 *
 * @author Ihsan Ghozi Zulfikar
 * Interface for game's object
 */

// Model interface
public interface GameInterface
{
    public void render(Graphics object);  // Render object.
    public void loop();    // Loop / refresh object.
}
