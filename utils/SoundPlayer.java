package utils;

import javax.sound.sampled.*;
import java.io.File;

public class SoundPlayer {

    private static Clip backgroundClip;

    /**
     * Plays a one-shot sound effect.
     */
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

    /**
     * Plays background music in a loop — does NOT stop existing playback.
     */
    public static void playBackground(String name) {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            // Already playing; don't restart
            return;
        }

        try {
            File file = new File("resources/sound/narutoTheme.wav");
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(stream);
            FloatControl gainControl = (FloatControl) backgroundClip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f); // Optional: reduce volume slightly
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception ex) {
            System.err.println("Error playing background music: " + ex.getMessage());
        }
    }

    /**
     * Stops the background music (optional use elsewhere if ever needed).
     */
    public static void stopBackground() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
            backgroundClip.close();
            backgroundClip = null;
        }
    }
}
