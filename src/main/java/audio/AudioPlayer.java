package audio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.Clip;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

// This class *plays* audio
public class AudioPlayer {
    private boolean on = true;

    private List<MediaPlayer> allPlayers = new ArrayList<>();

    private Map<MediaPlayer, Double> volumes = new HashMap<>();

    public MediaPlayer play(AudioData audioData) {
        var soundURL = this.getClass().getResource(audioData.path);
        Media media = new Media(soundURL.toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        if (audioData.loop) {
            mediaPlayer.setCycleCount(Clip.LOOP_CONTINUOUSLY);
        }
        mediaPlayer.play();
        mediaPlayer.setVolume(on ? Math.pow(10, audioData.volume / 20) : 0);

        allPlayers.add(mediaPlayer);
        volumes.put(mediaPlayer, Math.pow(10, audioData.volume / 20));

        return mediaPlayer;
    }

    public void setOn(boolean on) {
        this.on = on;
        if (!on) {
            for (var player : allPlayers) {
                player.setVolume(0);
            }
        } else {
            for (var player : allPlayers) {
                player.setVolume(volumes.get(player));
            }
        }
    }
}
