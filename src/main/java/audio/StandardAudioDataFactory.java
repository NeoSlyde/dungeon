package audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    public AudioData gameplayPeacefulMusic() {
        return new AudioData.Builder().withPath("/sounds/music.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData MainMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/menu.wav").withVolume(-20.0f).withLoop(true).build();
    }
}