package utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {
    public static void play(String name) {
        try {
            File file = new File("resources/sound/" + name + ".wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (Exception ex) {
            System.err.println("Error playing sound: " + ex.getMessage());
        }
    }
}
