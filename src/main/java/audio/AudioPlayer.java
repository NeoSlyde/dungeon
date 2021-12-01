package audio;

import javax.sound.sampled.Clip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// This class *plays* audio
public class AudioPlayer {
    public MediaPlayer play(AudioData audioData) {
        var soundURL = this.getClass().getResource(audioData.path);
        Media media = new Media(soundURL.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        if (audioData.loop) {
            mediaPlayer.setCycleCount(Clip.LOOP_CONTINUOUSLY);
        }
        mediaPlayer.play();
        mediaPlayer.setVolume(Math.pow(10, audioData.volume / 20));
        return mediaPlayer;
    }
}
