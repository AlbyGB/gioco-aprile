import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public class Player extends Entity {
    private Float energy;
    private boolean hasKey;

    public Player(PGraphics pg) {
        super(EntityType.PLAYER, 15, pg);
        this.energy = 0f;
        this.hasKey = false;
    }

    public void heal(int health) {
        this.health = health;
    }

    public void up() {
        coords.y -= 16;
    }

    public void down() {
        coords.y += 16;
    }

    public void left() {
        coords.x -= 16;
    }

    public void right() {
        coords.x += 16;
    }

    public Float getEnergy() {
        return energy;
    }

    public void setEnergy(Float energy) {
        this.energy = energy;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public boolean checkForKey() {
        return hasKey;
    }

    public void damage(int health) {
        this.health = health;
    }
}
