import java.util.List;
import java.util.Random;

abstract class Player extends Character {
    public Player(String name, List<ClassType> classes) {
        super(name, classes, 100, 0, 0, 0, 0, 0);
        initializeStats();
    }

    private void initializeStats() {
        Random rand = new Random();
        this.attackDamage = rand.nextInt(30) + 50;
        this.magicDamage = rand.nextInt(100);
        this.armor = 100;
        this.speed = rand.nextInt(100);
        this.magicResistance = rand.nextInt(100);
    }
}
