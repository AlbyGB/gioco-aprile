import processing.core.PGraphics;
import processing.core.PImage;

import java.util.HashMap;

public class EatableItem extends GameObject{
    private final EatableItemType eatableItemType;

    public EatableItem(Integer itemType, PGraphics pg) {
        super(pg);

        if (itemType % 2 == 0) {
            this.eatableItemType = EatableItemType.HEALTH;
            this.tileNumber = 135;
        } else {
            this.eatableItemType = EatableItemType.DAMAGE;
            this.tileNumber = 133;
        }
    }

    public boolean doesExist() {
        return doesExist;
    }

    public EatableItemType getEatableItemType() {
        return eatableItemType;
    }
}
