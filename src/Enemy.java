import processing.core.PGraphics;

import java.util.Random;

public class Enemy extends Entity {
    private boolean canAttack;


    public Enemy(PGraphics pg) {
        super(EntityType.ENEMY, 11, pg);
        canAttack = true;
    }

    // simple movements system
    public void move(MapGenerator mg) {
        var randomGenerator = new Random();
        boolean hasMoved = false;
        int movement;

        while (!hasMoved) {

            movement = randomGenerator.nextInt(0, 4);

            switch (movement) {
                case 0 -> {
                    if ((int) (this.getCoords().y / 16 - 1) >= 0 && !(mg.getMapScheme()[(int) (this.getCoords().x / 16)][(int) (this.getCoords().y / 16 - 1)] == 96)) {
                        this.getCoords().y -= 16;
                        hasMoved = true;
                    }
                }
                case 1 -> {
                    if ((int) (this.getCoords().y / 16 + 1) < mg.getMapHeight() && !(mg.getMapScheme()[(int) (this.getCoords().x / 16)][(int) (this.getCoords().y / 16 + 1)] == 96)) {
                        this.getCoords().y += 16;
                        hasMoved = true;
                    }
                }
                case 2 -> {
                    if ((int) (this.getCoords().x / 16 - 1) >= 0 && !(mg.getMapScheme()[(int) (this.getCoords().x / 16 - 1)][(int) (this.getCoords().y / 16)] == 96)) {
                        this.getCoords().x -= 16;
                        hasMoved = true;
                    }
                }
                case 3 -> {
                    if ((int) (this.getCoords().x / 16 + 1) < mg.getMapWidth() && !(mg.getMapScheme()[(int) (this.getCoords().x / 16 + 1)][(int) (this.getCoords().y / 16)] == 96)) {
                        this.getCoords().x += 16;
                        hasMoved = true;
                    }
                }
            }
        }
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean getCanAttackValue() {
        return canAttack;
    }
}