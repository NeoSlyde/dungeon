package audio;

public interface AudioDataFactory {
    AudioData gameplayPeacefulTheme();

    AudioData mainMenuTheme();

    AudioData pauseMenuTheme();

    AudioData selectSFX();

    AudioData closeSFX();

    AudioData pauseOpenSFX();

    AudioData pauseCloseSFX();

    AudioData battleMenuTheme();

    AudioData openDoorSFX();

    AudioData attackSFX();

    AudioData fireSFX();

    AudioData deathSFX();

    AudioData errorSFX();

    AudioData winSFX();

    AudioData encounterSFX();
}
