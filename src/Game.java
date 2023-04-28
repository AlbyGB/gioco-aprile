import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

import java.io.File;
import java.util.*;

public class Game extends PApplet {
    private final static HashMap<Integer, PImage> mapElements = new HashMap<>();

    private static File[] files;
    private Player player;
    private MapGenerator mapGenerator;
    private MapDisplayer mapDisplayer;
    private ArrayList<PVector> currentWallCoords;
    private Key doorKey;
    private Door door;
    private List<Enemy> enemies;
    private List<EatableItem> items;
    private Integer enemiesNumber;
    private Integer itemsNumber;

    public static void main(String[] args) {
        var game = new Game();
        game.run();
    }

    private void run() {
        PApplet.main("Game");
    }

    @Override
    public void setup() {
        files = new File("assets/kenneyrouguleike/Tiles/Colored").listFiles();
        var randomGenerator = new Random();

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

        doorKey = new Key(this.getGraphics());
        door = new Door(this.getGraphics());

        doorKey.setCoords(mapGenerator.getRandomFloorCell());
        door.setCoords(mapGenerator.getRandomFloorCell());

        enemiesNumber = randomGenerator.nextInt(6) + 1;
        itemsNumber = randomGenerator.nextInt(6) + 1;

        enemies = new ArrayList<>();
        items = new ArrayList<>();

        for (var e = 0; e < enemiesNumber; e++) {
            enemies.add(new Enemy(this.getGraphics()));
        }

        for (var it = 0; it < itemsNumber; it++) {
            items.add(new EatableItem(itemsNumber + it, this.getGraphics()));
        }

        enemies.forEach(enemy -> {
            enemy.setCoords(mapGenerator.getRandomFloorCell());
        });

        items.forEach(item -> {
            item.setCoords(mapGenerator.getRandomFloorCell());
        });
    }

    @Override
    public void draw() {
        mapDisplayer.display(mapGenerator.getMap());

        if (doorKey.isDrawable()) doorKey.draw(mapElements);

        door.draw(mapElements);

        items.forEach(item -> {
            item.draw(mapElements);
        });

        player.draw(mapElements);

        enemies.forEach(enemy -> {
            enemy.draw(mapElements);
        });

        // health bar
        fill(237, 231, 225, 60);
        rect(player.getCoords().x - 2, player.getCoords().y - 4, 20, 3);
        fill(237, 231, 225, 60);

        fill(0,128,0, 1000);
        rect(player.getCoords().x - 3, player.getCoords().y - 4, player.getHealth()*(1/5f), 3);
        fill(0,128,0, 1000);

        if (player.getCoords().x == doorKey.getCoords().x && player.getCoords().y == doorKey.getCoords().y) {
            doorKey.remove();
            player.setHasKey(true);
            door.setTileNumber(37);
        }

        if (player.getCoords().x == door.getCoords().x && player.getCoords().y == door.getCoords().y && player.checkForKey()) {
            setup();
        }

        if (player.getHealth() < 0) {
            setup();
        }

        for (EatableItem item : items) {
            if (item.getCoords().x == player.getCoords().x
                    && item.getCoords().y == player.getCoords().y
                    && item.getEatableItemType().equals(EatableItemType.HEALTH)
                    && item.doesExist()
                    && (player.getHealth() + 10) <= 100
            ) {
                player.heal(player.getHealth() + 10);
                item.remove();
            } else if (item.getCoords().x == player.getCoords().x
                    && item.getCoords().y == player.getCoords().y
                    && item.getEatableItemType().equals(EatableItemType.DAMAGE)
                    && item.doesExist()) {
                player.damage(player.getHealth() - 30);
                item.remove();
            }
        }
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
            case 114 -> setup();
            case 112 -> {
                if (looping) {
                    noLoop();
                } else {
                    loop();
                }
                options();
            }

        }
    }

    @Override
    public void settings() {
        size(800, 640);
    }

    private void options() {
        var pausedString = "PAUSED";
        var displayedString = """
                esc -> exit
                r -> restart
                """;

        textSize(50);
        text(pausedString, 200, 100);
        fill(255, 255, 255);

        textSize(100);
        text(displayedString, 200, getGraphics().height/2f - 50);
        fill(255, 255, 255);
    }
}