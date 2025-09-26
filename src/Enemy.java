import java.util.Random;

class Enemy extends Entity {
    public Enemy(String name, int attackDamage, int magicDamage, int armor, int speed, int magicResistance) {
        super(name, 100, attackDamage,  magicDamage, armor, speed, magicResistance);
    }

    @Override
    public int attack() {
        Random rand = new Random();
        double damage;
        double randomValue = rand.nextDouble(); // Generates random number from 0.0 to 1.0

        if (randomValue <= 0.6) {
            damage = 10.00;
            System.out.println("basic attack");
        } else if (randomValue <= 0.9) {
            damage = 20.00;
            System.out.println("skill");
        } else {
            damage = 30.00;
            System.out.println("ultimate");
        }
        return (int) damage;
    }
}
