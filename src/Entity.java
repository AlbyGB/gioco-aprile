import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public abstract class Entity {
    protected Integer health;
    protected final EntityType entityType;
    protected PVector coords;
    protected final Integer tileNumber;
    protected final PGraphics pg;

    public Entity(EntityType entityType, Integer tileNumber, PGraphics pg) {
        this.tileNumber = tileNumber;
        this.pg = pg;
        this.health = 100;
        this.entityType = entityType;
        this.coords = new PVector();
    }

    public void draw(HashMap<Integer, PImage> mapElements) {
        pg.image(mapElements.get(tileNumber), coords.x, coords.y);
    }

    public void setCoords(PVector coords) {
        this.coords.x = coords.x;
        this.coords.y = coords.y;
    }

    public PVector getCoords() {
        return coords;
    }

    public Integer getTileNumber() {
        return tileNumber;
    }

    public PGraphics getPg() {
        return pg;
    }

    public Integer getHealth() {
        return health;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}