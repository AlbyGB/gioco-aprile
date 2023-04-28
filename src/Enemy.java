import processing.core.PGraphics;

import java.util.Random;

public class Enemy extends Entity {
    private boolean canAttack;


    public Enemy(PGraphics pg) {
        super(EntityType.ENEMY, 11, pg);
        canAttack = true;
    }

    // simple movements system
    public void move(Integer[][] mapScheme, MapGenerator mg) {
        var randomGenerator = new Random();

        var movement = randomGenerator.nextInt(0, 4);

        // int modX = (int) this.coords.x / 16;
        // int modY = (int) this.coords.y / 16;

        if (movement == 0) {
            this.coords.y -= 16;
        } else if (movement == 1) {
            this.coords.y += 16;
        } else if (movement == 2) {
            this.coords.x -= 16;
        } else {
            this.coords.x += 16;
        }

        // check for wall collisions
        if ((this.coords.y/16)-16 < 0) {
            this.coords.y += 16;
        } else if ((this.coords.y/16)+16 > mg.getMapHeight()) {
            this.coords.y -= 16;
        } else if ((this.coords.x/16)-16 < 0) {
            this.coords.x += 16;
        } else if ((this.coords.x/16)+16 > mg.getMapWidth()) {
            this.coords.x -= 16;
        }
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public boolean getCanAttackValue() {
        return canAttack;
    }
}