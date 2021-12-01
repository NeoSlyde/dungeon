package audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    public AudioData gameplayPeacefulTheme() {
        return new AudioData.Builder().withPath("/sounds/music.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData mainMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/menu.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData pauseMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/pause.wav").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData selectSFX() {
        return new AudioData.Builder().withPath("/sounds/select.wav").withVolume(-10.0f).build();
    }

    @Override
    public AudioData closeSFX() {
        return new AudioData.Builder().withPath("/sounds/close.wav").withVolume(-10.0f).build();
    }

    @Override
    public AudioData pauseOpenSFX() {
        return new AudioData.Builder().withPath("/sounds/pauseopen.wav").withVolume(-10.0f).build();
    }

    @Override
    public AudioData pauseCloseSFX() {
        return new AudioData.Builder().withPath("/sounds/pauseclose.wav").withVolume(-10.0f).build();
    }

    @Override
    public AudioData battleMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/fight.wav").withVolume(-12.0f).withLoop(true).build();
    }

    @Override
    public AudioData openDoorSFX() {
        return new AudioData.Builder().withPath("/sounds/door.wav").withVolume(-10.0f).build();
    }

    @Override
    public AudioData attackSFX() {
        return new AudioData.Builder().withPath("/sounds/hit.wav").withVolume(6.0f).build();
    }

    @Override
    public AudioData fireSFX() {
        return new AudioData.Builder().withPath("/sounds/fire.wav").withVolume(0.0f).build();
    }

    @Override
    public AudioData deathSFX() {
        return new AudioData.Builder().withPath("/sounds/death.wav").withVolume(0.0f).build();
    }
}
