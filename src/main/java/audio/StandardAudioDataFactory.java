package audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    public AudioData gameplayPeacefulMusic() {
        return new AudioData.Builder().withPath("/sounds/music.wav").withVolume(-20.0f).withLoop(true).build();
    }
}
