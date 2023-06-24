/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package viewmodel;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Bgm {
    private static final float DEFAULT_VOLUME = 0.1f; //  volume level
    
    public Clip playSound(Clip clip, String filename) {
        try {
            // Get Bgm from assets
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(new File("assets/" + filename).getAbsoluteFile());
            clip = AudioSystem.getClip();
            
            clip.open(audioInput);
            
            // Adjust volume
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float volume = convertToDecibels(DEFAULT_VOLUME);
            gainControl.setValue(volume);
            
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        
        return clip;
    }
    
    // Stop
    public void stopSound(Clip clip) {
        clip.stop();
    }

    // convert linear gain value to decibels
    private float convertToDecibels(float gain) {
        return (float) (20.0 * Math.log10(gain));
    }
}
