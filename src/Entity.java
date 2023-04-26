public abstract class Entity {
    private Integer health;
    private final EntityType entityType;

    public Entity(EntityType entityType) {
        this.health = 100;
        this.entityType = entityType;
    }

    public Integer getHealth() {
        return health;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}