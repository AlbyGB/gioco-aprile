import processing.core.PGraphics;
import processing.core.PImage;

public class MapDisplayer {
    private PGraphics pg;
    private Integer screenWidth;
    private Integer screenHeight;

    public MapDisplayer(PGraphics pg) {
        this.pg = pg;
        this.screenWidth = pg.width/16;
        this.screenHeight = pg.height/16;
    }

    public void display(PImage[][] map) {
        for (var c = 0; c < screenWidth; c++) {
            for (var r = 0; r < screenHeight; r++) {
                pg.image(map[c][r], c*16, r*16);
            }
        }
    }
}
