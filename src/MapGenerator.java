import processing.core.PGraphics;
import processing.core.PImage;

import java.util.*;

public class MapGenerator {
    // private static final Integer WIDTH = 800;
    // private static final Integer HEIGHT = 600;
    private static final Integer CELL = 16;
    private static final Integer FLOOR_CELLS = 5000;

    private final PGraphics pg;
    private final HashMap<Integer, PImage> mapElements;
    private ArrayList<PImage> tiles;
    private final Integer[][] mapScheme;
    private final PImage[][] map;
    private final Integer mapWidth;
    private final Integer mapHeight;

    public MapGenerator(List<PImage> loadedTiles, PGraphics pg) {
        this.pg = pg;
        this.tiles = new ArrayList<>(loadedTiles);
        this.mapElements = new HashMap<>();
        this.mapWidth = this.pg.width/CELL;
        this.mapHeight = this.pg.height/CELL;
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
        var halfWidth = (int) (mapWidth*0.5f);
        var halfHeight = (int) (mapHeight*0.5f);
        var startCell = mapScheme[(int) (mapWidth*0.5f)][(int) (mapHeight*0.5f)]; // centro della mappa
        var randomGenerator = new Random();
        Integer[] possibleSteps = new Integer[3];

        possibleSteps[0] = -1;
        possibleSteps[1] = 0;
        possibleSteps[2] = 1;

        startCell = 96; // numero della tile del pavimento
        floorCellsCounter += 1;

        var currentWidth = halfWidth;
        var currentHeight = halfHeight;

        while (floorCellsCounter <= FLOOR_CELLS) {
            var randomStep = randomGenerator.nextInt( 3);

            if (randomStep == 0) {
                var heightRandomStep = randomGenerator.nextInt(3);

                if (currentHeight+possibleSteps[heightRandomStep] < 0 || currentHeight+possibleSteps[heightRandomStep] >= mapHeight) {
                    continue;
                } else {
                    if (mapScheme[currentWidth][(currentHeight+possibleSteps[heightRandomStep])] == 67) {
                        mapScheme[currentWidth][(currentHeight+possibleSteps[heightRandomStep])] = 96;
                    }
                }

                currentHeight += possibleSteps[heightRandomStep];
            }

            if (currentWidth+possibleSteps[randomStep] < 0 || currentWidth+possibleSteps[randomStep] >= mapWidth) {
                continue;
            } else {
                if (mapScheme[currentWidth+possibleSteps[randomStep]][(currentHeight)] == 67) {
                    mapScheme[currentWidth+possibleSteps[randomStep]][(currentHeight)] = 96;
                }
            }


            currentWidth += possibleSteps[randomStep];

            floorCellsCounter += 1;
        }

        for (var c = 0; c < mapWidth; c++) {
            for (var r = 0; r < mapHeight; r++) {
                map[c][r] = mapElements.get(mapScheme[c][r]);
            }
        }
    }

    public PGraphics getPg() {
        return pg;
    }

    public Integer[][] getMapScheme() {
        return mapScheme;
    }

    public Integer getMapWidth() {
        return mapWidth;
    }

    public Integer getMapHeight() {
        return mapHeight;
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
