import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;

public class Game extends PApplet {
    private final static HashMap<Integer, PImage> mapElements = new HashMap<>();
    private Player player;
    private MapGenerator mapGenerator;
    private MapDisplayer mapDisplayer;
    private ArrayList<PVector> currentWallCoords;

    public static void main(String[] args) {
        var game = new Game();
        game.run();
    }

    private void run() {
        PApplet.main("Game");
    }

    @Override
    public void setup() {
        File[] files = new File("assets/kenneyrouguleike/Tiles/Colored").listFiles();

        var loadedTiles = Arrays.stream(Objects.requireNonNull(files)).map(file -> loadImage(file.getPath())).toList();
        loadedTiles.forEach(tile -> tile.resize(16, 16));

        for (var i = 0; i < loadedTiles.size(); i++) {
            mapElements.put(i, loadedTiles.get(i));
        }

        mapGenerator = new MapGenerator(mapElements, this.getGraphics());
        mapDisplayer = new MapDisplayer(this.getGraphics());

        mapGenerator.generateMap();

        player = new Player(getGraphics());
        player.setCoords(mapGenerator.getRandomFloorCell());

        currentWallCoords = mapGenerator.getWallsCoords();
    }

    @Override
    public void draw() {
        mapDisplayer.display(mapGenerator.getMap());
        player.draw(mapElements);
    }

    @Override
    public void keyPressed() {
        switch (key) {
            case 119 -> { // mapGenerator.getMapScheme()[(int) (player.getCoords().x/16 - 1)][(int) (player.getCoords().y/16)] != 96)
                if (player.getCoords().y/16 - 1 >= 0) {
                    if (mapGenerator.getMapScheme()[(int) (player.getCoords().x/16)][(int) (player.getCoords().y/16 - 1)] != 96) {
                        player.up();
                    }
                }
            }
            case 97 -> {
                if (player.getCoords().x/16 - 1 >= 0) {
                    if (mapGenerator.getMapScheme()[(int) (player.getCoords().x/16 - 1)][(int) (player.getCoords().y/16)] != 96) {
                        player.left();
                    }
                }
            }
            case 115 -> {
                if (player.getCoords().y/16 + 1 < mapGenerator.getMapHeight()) {
                    if (mapGenerator.getMapScheme()[(int) (player.getCoords().x/16)][(int) (player.getCoords().y/16) + 1] != 96) {
                        player.down();
                    }
                }
            }
            case 100 -> {
                if (player.getCoords().x/16 + 1 < mapGenerator.getMapWidth()) {
                    if (mapGenerator.getMapScheme()[(int) (player.getCoords().x/16 + 1)][(int) (player.getCoords().y/16)] != 96) {
                        player.right();
                    }
                }
            }
        }
    }

    @Override
    public void settings() {
        size(800, 640);
    }
}