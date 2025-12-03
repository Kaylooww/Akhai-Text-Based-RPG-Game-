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
    protected double evasiveness;
    protected List<StatusEffect> statusEffects = new ArrayList<>();

    protected int maxHealth;
    protected double maxEvasiveness;

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
        this.maxEvasiveness = evasiveness;
    }

    // Abstract attack method for player to use
    public abstract int attack();

    public int takeDamage(double damage, int defense, double physicalResistance, double magicResistance) {
        double actualDamage = damage - defense;
        //added this simple if statement to counter negative damage issues
        if(actualDamage < 0){
            actualDamage = 1;
        }
        int oldHealth = health;
        health -= (int) actualDamage;

        if (health <= 0) {
            health = 0;
        }

        return (int) actualDamage;
    }

    public void addStatusEffect(StatusEffect statusEffect) {
        statusEffects.add(statusEffect);
    }
    public void removeStatusEffect(StatusEffect statusEffect) {
        statusEffects.remove(statusEffect);
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
                evasiveness += evasiveness * value;
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
                evasiveness += value;
        }
    }
    public void checkStatusEffect(){
        for(int i = 0; i < statusEffects.size(); i++){
            statusEffects.get(i).onTurnStart(this);
            statusEffects.get(i).onTurnEnd(this);
        }
    }
    public boolean hasEffect(StatusEffect statusEffect){
        return statusEffects.contains(statusEffect);
    }

    public void setHealth(int health) {this.health = health;}
    public void setShield(int shield) {this.shield = shield;}
    public void setPhysicalDamage(int physicalDamage) {this.physicalDamage = physicalDamage;}
    public void setMagicDamage(int magicDamage) {this.magicDamage = magicDamage;}
    public void setSpeed(int speed) {this.speed = speed;}
    public void setEvasiveness(double evasiveness) {
        this.evasiveness = evasiveness;
    }

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
    public double getEvasiveness(){
        return evasiveness;
    }
    public double getMaxEvasiveness(){
        return maxEvasiveness;
    }
    public List<StatusEffect> getStatusEffects() {
        return statusEffects;
    }
}