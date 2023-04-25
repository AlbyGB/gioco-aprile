import processing.core.PApplet;

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
        var mapGenerator = new MapGenerator(loadedTiles, this.getGraphics());
    }

    @Override
    public void draw() {

    }
}