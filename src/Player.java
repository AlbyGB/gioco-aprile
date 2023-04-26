public class Player extends Entity {
    private Float energy;

    public Player() {
        super(EntityType.PLAYER);
        this.energy = 0f;
    }
}
