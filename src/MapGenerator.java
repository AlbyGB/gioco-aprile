import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapGenerator {
    // private static final Integer WIDTH = 800;
    // private static final Integer HEIGHT = 600;
    private static final Integer CELL = 16;
    private static final Integer FLOOR_CELLS = 500;

    private final PGraphics pg;
    private final HashMap<Integer, PImage> mapElements;
    private final ArrayList<PImage> tiles;
    private final Integer[][] mapScheme;
    private final PImage[][] map;
    private final Integer mapWidth;
    private final Integer mapHeight;

    public MapGenerator(List<PImage> loadedTiles, PGraphics pg) {
        this.pg = pg;
        this.tiles = new ArrayList<>(loadedTiles);
        this.mapElements = new HashMap<>();
        this.mapWidth = this.pg.width*CELL;
        this.mapHeight = this.pg.height*CELL;
        this.mapScheme = new Integer[mapWidth][mapHeight];
        this.map = new PImage[mapWidth][mapHeight];

        for (var i = 0; i < tiles.size(); i++) {
            mapElements.put(i, tiles.get(i));
        }
    }

    public void generateMap() {
        // tile 67 per il muro
        // tile 90 per la chiave
        for (var c = 0; c < mapWidth; c++) {
            for (var r = 0; r < mapHeight; r++) {
                mapScheme[c][r] = 67;
            }
        }

        var floorCellsCounter = 0;
        var startCell = mapScheme[(int) (mapWidth*0.5f)][(int) (mapHeight*0.5f)]; // centro della mappa

        startCell = 96; // numero della tile del pavimento
        floorCellsCounter += 1;

        while (floorCellsCounter <= FLOOR_CELLS) {


            floorCellsCounter += 1;
        }
    }

    public static Integer getCELL() {
        return CELL;
    }

    public HashMap<Integer, PImage> getMapElements() {
        return mapElements;
    }

    public PImage[][] getMap() {
        return map;
    }

    public ArrayList<PImage> getTiles() {
        return tiles;
    }
}
