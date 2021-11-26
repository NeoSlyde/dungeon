package audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    public AudioData gameplayPeacefulMusic() {
        return new AudioData.Builder().withPath("/sounds/music.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData MainMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/menu.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData PauseMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/pause.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData SelectSoundEffect() {
        return new AudioData.Builder().withPath("/sounds/select.wav").withVolume(-10.0f).withLoop(false).build();
    }

    @Override
    public AudioData CloseSoundEffect() {
        return new AudioData.Builder().withPath("/sounds/close.wav").withVolume(-10.0f).withLoop(false).build();
    }

    @Override
    public AudioData BruhSoundEffect() {
        return new AudioData.Builder().withPath("/sounds/bruh.wav").withVolume(0).withLoop(false).build();
    }

    @Override
    public AudioData PauseOpenSoundEffect() {
        return new AudioData.Builder().withPath("/sounds/pauseopen.wav").withVolume(-10.0f).withLoop(false).build();
    }

    @Override
    public AudioData PauseCloseSoundEffect() {
        return new AudioData.Builder().withPath("/sounds/pauseclose.wav").withVolume(-10.0f).withLoop(false).build();
    }

}
