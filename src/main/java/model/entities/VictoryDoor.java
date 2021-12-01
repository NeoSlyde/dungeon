package model.entities;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import model.Room;
import model.misc.Direction;

public class VictoryDoor extends Door {
    public VictoryDoor(Room room, Direction side, double pos, AudioDataFactory audioDataFactory,
            AudioPlayer audioPlayer) {
        super(room, side, pos, audioDataFactory, audioPlayer);
    }

    @Override
    public void use(LivingEntity e) {
        if (!(e instanceof Player))
            return;
        var player = (Player) e;
        player.setHasWon(true);
    }
}
