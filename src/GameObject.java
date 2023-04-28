import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;

import java.util.HashMap;

public abstract class GameObject {
    protected PGraphics pg;
    protected PVector coords;
    protected Integer tileNumber;
    protected boolean doesExist;

    public GameObject(PGraphics pg) {
        this.pg = pg;
        this.doesExist = true;
        this.coords = new PVector(0, 0);
    }

    public void setTileNumber(Integer tileNumber) {
        this.tileNumber = tileNumber;
    }

    public void remove() {
        this.tileNumber = 17;
        doesExist = false;
    }

    public void draw(HashMap<Integer, PImage> mapElements) {
        pg.image(mapElements.get(tileNumber), coords.x, coords.y);
    }

    public PVector getCoords() {
        return coords;
    }

    public void setCoords(PVector coords) {
        this.coords.x = coords.x;
        this.coords.y = coords.y;
    }
}
