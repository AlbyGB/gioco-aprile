public class EatableItem {
    private final EatableItemType eatableItemType;

    public EatableItem(EatableItemType eatableItemType) {
        this.eatableItemType = eatableItemType;
    }

    public EatableItemType getEatableItemType() {
        return eatableItemType;
    }
}
