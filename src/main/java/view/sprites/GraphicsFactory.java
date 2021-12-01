package view.sprites;

import javafx.scene.paint.ImagePattern;

public interface GraphicsFactory {
    LivingEntitySprite player();

    LivingEntitySprite skeleton();

    LivingEntitySprite goblin();

    LivingEntitySprite ghost();

    Sprite wall();

    Sprite door();

    Sprite chest();

    Sprite healingPotion();

    Sprite manaPotion();

    ImagePattern floorPattern();

    Sprite gameOver();

    Sprite victory();
}
