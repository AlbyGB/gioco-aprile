import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class Game extends PApplet {
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
        System.out.println(loadedTiles);
        var mapGenerator = new MapGenerator(loadedTiles, this.getGraphics());
        var mapDisplayer = new MapDisplayer(this.getGraphics());
        mapGenerator.generateMap();
        mapDisplayer.display(mapGenerator.getMap());
    }

    @Override
    public void draw() {

    }

    @Override
    public void settings() {
        size(800, 640);
    }
}