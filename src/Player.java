//Disregarded

import java.util.List;
import java.util.Random;

abstract class Player extends Character {
    public Player(String name, int health, List<ClassType> classes, double basicAttack, double skillAttack, double ultimateAttack) {
        super(name, health, classes, basicAttack, skillAttack, ultimateAttack, 20, 20, 50, 50.0, 50.0, 50);
    }
//
    /*private void initializeStats() {
        Random rand = new Random();
        this.attackDamage = rand.nextInt(30) + 50;
        this.magicDamage = rand.nextInt(100);
        this.defense = 100;
        this.speed = rand.nextInt(100);
        this.magicResistance = rand.nextInt(100);
    }*/
}
