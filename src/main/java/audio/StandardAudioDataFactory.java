package audio;

public class StandardAudioDataFactory implements AudioDataFactory {
    public AudioData gameplayPeacefulTheme() {
        return new AudioData.Builder().withPath("/sounds/music.mp3").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData mainMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/menu.mp3").withVolume(-20.0f).withLoop(true).build();
    }

    @Override
    public AudioData pauseMenuTheme() {
        return new AudioData.Builder().withPath("/sounds/pause.mp3").withVolume(-20.0f).withLoop(true).build();
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
        return new AudioData.Builder().withPath("/sounds/fight.mp3").withVolume(-12.0f).withLoop(true).build();
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

    @Override
    public AudioData errorSFX() {
        return new AudioData.Builder().withPath("/sounds/error.wav").withVolume(0.0f).build();
    }

    @Override
    public AudioData winSFX() {
        return new AudioData.Builder().withPath("/sounds/win.wav").withVolume(0.0f).build();
    }

    @Override
    public AudioData encounterSFX() {
        return new AudioData.Builder().withPath("/sounds/encounter.wav").withVolume(0.0f).build();
    }

    @Override
    public AudioData chestSFX() {
        return new AudioData.Builder().withPath("/sounds/open.wav").withVolume(0.0f).build();
    }
}
