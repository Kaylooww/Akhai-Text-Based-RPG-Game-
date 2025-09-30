package Entities.Enemies;

import Entities.Entity;

import java.util.Random;

public class Enemy extends Entity {
    public Enemy(String name, int health, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed) {
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    @Override
    public int attack() {
        Random rand = new Random();
        double damage;
        double randomValue = rand.nextDouble();
        //
        if (randomValue <= 0.6) {
            damage = physicalDamage * 0.8;
            System.out.println(name + " uses basic attack!");
        } else if (randomValue <= 0.9) {
            damage = physicalDamage * 1.2;
            System.out.println(name + " uses special skill!");
        } else {
            damage = physicalDamage * 1.5;
            System.out.println(name + " uses ULTIMATE attack!");
        }
        return (int) damage;
    }

    @Override
    public int takeDamage(double damage, int defense, double physicalResistance, double magicResistance) {
        double actualDamage = damage - defense; // Simple damage reduction
        if (actualDamage < 1) actualDamage = 1; // Minimum 1 damage

        int oldHealth = health;
        health -= (int) actualDamage;

        if (health <= 0) {
            health = 0;
            System.out.println("🎯 " + name + " has been defeated!");
        } else if (health < oldHealth * 0.3) {
            System.out.println("⚠️  " + name + " is looking weak!");
        }

        return (int) actualDamage;
    }
}