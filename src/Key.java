import processing.core.PGraphics;

public class Key extends GameObject {
    private boolean isDrawable;

    public Key(PGraphics pg) {
        super(pg);
        this.tileNumber = 90;
        this.isDrawable = true;
    }

    public boolean isDrawable() {
        return isDrawable;
    }

    public void setDrawable(boolean drawable) {
        isDrawable = drawable;
    }
}
