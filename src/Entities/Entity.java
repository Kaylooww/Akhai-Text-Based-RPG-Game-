package Entities;

import StatusEffects.StatusEffect;
import java.util.*;

public abstract class Entity {
    protected String name;
    protected int health;
    protected int shield;
    protected int physicalDamage;
    protected int magicDamage;
    protected int defense;
    protected double physicalResistance;
    protected double magicResistance;
    protected int speed;
    protected double accuracy = 0.95;
    protected List<StatusEffect> statusEffects = new ArrayList<>();

    protected int maxHealth;
    protected int originalPhysicalDamage;
    protected int originalMagicDamage;
    protected int originalDefense;
    protected double originalPhysicalResistance;
    protected double originalMagicResistance;
    protected int originalSpeed;
    protected int originalAccuracy;
    protected double evasion = 0.0;
    protected int temporaryShield = 0;

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

    public void addStatusEffect(StatusEffect statusEffect) {
        statusEffects.add(statusEffect);
    }
    public void removeStatusEffect(StatusEffect statusEffect) {
        statusEffects.remove(statusEffect);
    }
    public void removeAllStatusEffect(){
        statusEffects.clear();
    }
    public void modifyStatPercentage(StatType statType, double value) {
        switch (statType) {
            case PHYSICAL_DAMAGE:
                physicalDamage += physicalDamage * value;
                break;
            case MAGIC_DAMAGE:
                magicDamage += magicDamage * value;
                break;
            case DEFENSE:
                defense += defense * value;
                break;
            case PHYSICAL_RESISTANCE:
                physicalResistance += physicalResistance * value;
                break;
            case MAGIC_RESISTANCE:
                magicResistance += magicResistance * value;
                break;
            case SPEED:
                speed += speed * value;
                break;
            case ACCURACY:
                accuracy += accuracy * value;
        }
    }
    public void modifyStatFlat(StatType statType, int value){
        switch (statType) {
            case PHYSICAL_DAMAGE:
                physicalDamage += value;
                break;
            case MAGIC_DAMAGE:
                magicDamage += value;
                break;
            case DEFENSE:
                defense += value;
                break;
            case PHYSICAL_RESISTANCE:
                physicalResistance += value;
                break;
            case MAGIC_RESISTANCE:
                magicResistance += value;
                break;
            case SPEED:
                speed += value;
                break;
            case ACCURACY:
                accuracy += value;
        }
    }
    public void checkStatusEffect(){
        for(int i = 0; i < statusEffects.size(); i++){
            statusEffects.get(i).onTurnStart(this);
            statusEffects.get(i).onTurnEnd(this);
        }
    } //This method shouldn't exist (onTurnStart | onTurnEnd should be placed before | after the Entity makes a move)
    public boolean hasEffect(StatusEffect statusEffect){
        return statusEffects.contains(statusEffect);
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
    public List<StatusEffect> getStatusEffects() {
        return statusEffects;
    }
}
