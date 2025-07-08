package sound;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    public void playBladeSound() {
        playSound("assets/blade.mp3");
    }

    public void playShieldSound() {
        playSound("assets/shield.mp3");
    }

    private void playSound(String path) {
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(new File(path));
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
