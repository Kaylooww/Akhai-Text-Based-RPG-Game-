package Entities.Enemies;

import Entities.Entity;

public abstract class Enemy extends Entity {
    public Enemy(String name, int health, int physicalDamage, int magicDamage, int defense,
                 double physicalResistance, double magicResistance, int speed) {
        super(name, health, physicalDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    // Default attack implementation for enemies that don't override it
    @Override
    public int attack() {
        return getPhysicalDamage(); // Default basic attack
    }
}