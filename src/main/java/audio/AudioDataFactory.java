package audio;

public interface AudioDataFactory {
    AudioData gameplayPeacefulMusic();

    AudioData mainMenuTheme();

    AudioData pauseMenuTheme();

    AudioData selectSoundEffect();

    AudioData closeSoundEffect();

    AudioData bruhSoundEffect();

    AudioData pauseOpenSoundEffect();

    AudioData pauseCloseSoundEffect();

    AudioData battleMenuTheme();

    AudioData openDoorSoundEffect();

    AudioData attackSoundEffect();

    AudioData fireSoundEffect();

    AudioData deathSFX();
}
