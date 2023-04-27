import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public class Player extends Entity {
    private Float energy;

    public Player(PGraphics pg) {
        super(EntityType.PLAYER, 15, pg);
        this.energy = 0f;
    }

    public void setCoords(PVector coords) {
        this.coords.x = coords.x;
        this.coords.y = coords.y;
    }

    public void draw(HashMap<Integer, PImage> mapElements) {
        pg.image(mapElements.get(tileNumber), coords.x, coords.y);
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
}
