package Entities;

public abstract class Entity {
    protected String name;
    protected int health;
    protected int maxHealth;
    protected int shield;
    protected int physicalDamage;
    protected int magicDamage;
    protected int defense;
    protected double physicalResistance;
    protected double magicResistance;
    protected int speed;
    protected double accuracy = 0.95;
//
    public Entity(String name, int health, int physicalDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.physicalDamage = physicalDamage;
        this.magicDamage = magicDamage;
        this.defense = defense;
        this.physicalResistance = physicalResistance;
        this.magicResistance = magicResistance;
        this.speed = speed;
    }

    //This method is mostly used for the Enemy class
    public abstract int attack();

    public int takeDamage(double damage, int defense, double physicalResistance, double magicResistance) {
        double actualDamage = damage - defense;
        int oldHealth = health;
        health -= (int) actualDamage;

        if (health <= 0) {
            health = 0;
            System.out.println("💀 " + name + " has been defeated!");
        }

        return (int) actualDamage;
    }

    public void setHealth(int health) {this.health = health;}
    public void setShield(int shield) {this.shield = shield;}
    public void setPhysicalDamage(int physicalDamage) {this.physicalDamage = physicalDamage;}
    public void setMagicDamage(int magicDamage) {this.magicDamage = magicDamage;}
    public void setSpeed(int speed) {this.speed = speed;}

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getShield() {return shield;}
    public int getPhysicalDamage(){
        return physicalDamage;
    }
    public int getMagicDamage(){
        return magicDamage;
    }
    public int getDefense(){
        return defense;
    }
    public double getPhysicalResistance(){
        return physicalResistance;
    }
    public double getMagicResistance(){
        return magicResistance;
    }
    public int getSpeed(){
        return speed;
    }
    public double getAccuracy(){
        return accuracy;
    }
}
