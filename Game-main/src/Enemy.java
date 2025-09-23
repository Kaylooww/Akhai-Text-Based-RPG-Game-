import java.util.Random;

class Enemy extends Entity {
    public Enemy(String name, int attackDamage, int magicDamage, int armor, int speed, int magicResistance) {
        super(name, 100, attackDamage,  magicDamage, armor, speed, magicResistance);
    }

    @Override
    public int attack() {
        Random rand = new Random();
        return attackDamage + rand.nextInt(11) - 5;
    }
}
