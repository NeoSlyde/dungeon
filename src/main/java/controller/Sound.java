package controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/sounds/music.wav");
        soundURL[1] = getClass().getResource("/sounds/spin.wav");
        soundURL[2] = getClass().getResource("/sounds/door.wav");
        soundURL[3] = getClass().getResource("/sounds/bruh.wav");
        soundURL[4] = getClass().getResource("/sounds/fight.wav");
    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void replay(){
        clip.setFramePosition(0);
    }

    public void setVolume(float level) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(level);
        clip.start();
    }
}
