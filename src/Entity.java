import processing.core.PGraphics;
import processing.core.PVector;

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

    public Integer getHealth() {
        return health;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}