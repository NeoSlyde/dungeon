package model.entities;

import audio.AudioDataFactory;
import audio.AudioPlayer;
import model.Room;
import model.entities.monsters.Monster;
import model.misc.Vec2;

public class TrappedChest extends Chest {
    private EntityFactory monsterFactory;

    public TrappedChest(Room room, Vec2 position, AudioDataFactory audioDataFactory, AudioPlayer audioPlayer,
            EntityFactory monsterFactory) {
        super(room, position, audioDataFactory, audioPlayer);
        this.monsterFactory = monsterFactory;
    }

    @Override
    public void use(LivingEntity user) {
        super.use(user);
        if (!(user instanceof Player))
            return;
        Player player = (Player) user;
        player.setEnemy((Monster) monsterFactory.generate(user.getRoom(), user.getPosition()));
    }
}
