package Entities.Characters;

import Entities.Entity;
import Items.*;
import Items.Weapons.Weapon;
import Skills.*;
import java.util.Random;

public abstract class Character extends Entity {
    protected int maxEnergy = 100;
    protected int energy = 0;
    protected int level;
    protected int experience = 0;
    protected int experienceNeeded = 50;
    protected int currency = 100; //money
    protected Inventory inventory = new Inventory();
    protected Item equippedWeapon;
    protected Skill basicAttack;
    protected Skill skillAttack;
    protected Skill ultimateAttack;

    protected ClassType classType;
    protected boolean hasResurrected = false; // New resurrection flag

    protected int ultimateCounter = 0;
    protected int maxUltimateCounter = 8;
    protected Random random = new Random();

    public Character(String name, int health, ClassType classType, int physicalDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed) {
        super(name, health, physicalDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
        this.classType = classType;
        this.equippedWeapon = new Weapon();
        this.basicAttack = new UnarmedSkill("Punch", "", 0.5, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        this.skillAttack = new UnarmedSkill("Super Punch", "", 0.8, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        this.ultimateAttack = new UnarmedSkill("Ultimate Punch", "", 1.2, 80, DamageType.PHYSICAL, TargetType.SINGLE);
    }

    public int getUltimateCounter() {
        return ultimateCounter;
    }

    public int getMaxUltimateCounter() {
        return maxUltimateCounter;
    }

    public void addUltimateCounter(int amount) {
        ultimateCounter = Math.min(ultimateCounter + amount, maxUltimateCounter);
        System.out.println("⭐ Ultimate Charge: " + ultimateCounter + "/" + maxUltimateCounter);
    }

    public void resetUltimateCounter() {
        ultimateCounter = 0;
    }

    public void generateEnergyFromDamage() {
        int energyGained = 5 + random.nextInt(10) + 1; //5 + random 1-10
        this.energy = Math.min(this.energy + energyGained, this.maxEnergy);
        System.out.println("💫 Gained " + energyGained + " energy from taking damage! (" + energy + "/" + maxEnergy + ")");
    }

    public void generateEnergy(int amount) {
        this.energy = Math.min(this.energy + amount, this.maxEnergy);
        System.out.println("⚡ Generated " + amount + " energy! (" + energy + "/" + maxEnergy + ")");
    }

    public boolean consumeEnergy(int amount) {
        if (energy >= amount) {
            energy -= amount;
            return true;
        }
        return false;
    }

    public void gainExperience(int exp) {
        experience += exp;
        while (experience >= experienceNeeded && level <= 30) { // Added level cap
            levelUp();
        }
        System.out.println("Gained " + exp + " experience!");
    }
    public void levelUp() {
        experience -= experienceNeeded;
        level++;
        experienceNeeded += 1;
        System.out.println(name + " leveled up to level " + level + "!");

        // Improve stats on level up
        int oldMaxHealth = maxHealth;
        maxHealth += 5;
        health += 5; // Also increase current health
        physicalDamage += 2;
        magicDamage += 2;
        defense += 1;
        magicResistance += 0.01;
        physicalResistance += 0.01;


        System.out.println("Max HP increased: " + oldMaxHealth + " → " + maxHealth);
    }

    // New resurrection method
    public void resurrect() {
        if (hasResurrected) {
            System.out.println("❌ " + name + " cannot resurrect again! Resurrection already used.");
            return;
        }

        if (health > 0) {
            System.out.println("❌ " + name + " doesn't need resurrection! Health is still positive.");
            return;
        }

        System.out.println("✨ ✨ ✨ DIVINE INTERVENTION! ✨ ✨ ✨");
        System.out.println(name + " has been granted a second chance!");

        //Resurrect with 50% of max health and reset ultimate counter
        health = maxHealth / 2;
        hasResurrected = true;
        resetUltimateCounter(); //Reset ultimate charges on resurrection

        System.out.println(name + " resurrects with " + health + " HP!");
        System.out.println("Ultimate charges have been reset!");
        System.out.println("This resurrection has been consumed and cannot be used again.");
    }
    public boolean hasResurrected() {
        return hasResurrected;
    }
    // Update displayStats to show resurrection status
    public void displayStats() {
        System.out.println("\n=== " + name + "'s STATS ===");
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience + "/" + experienceNeeded);
        System.out.println("Class: " + classType);
        System.out.println("HP: " + health + "/" + maxHealth);
        System.out.println("Attack: " + physicalDamage);
        System.out.println("Armor: " + defense);
        System.out.println("Speed: " + speed);
        System.out.println("Energy: " + energy + "/" + maxEnergy);
        System.out.println("Ultimate Charge: " + ultimateCounter + "/" + maxUltimateCounter);
        System.out.println("Magic Defense: " + magicResistance);
        System.out.println("Magic Damage: " + magicDamage);
        System.out.println("Resurrection: " + (hasResurrected ? "❌ USED" : "✅ AVAILABLE"));
        System.out.println("====================");
    }
    public void displayInventory(){
        inventory.cleanInventory();
        Item[] items = inventory.getItems();
        System.out.println("========== "+name+"'s INVENTORY ==========");
        System.out.println("CURRENCY: "+currency);
        System.out.println("\nWeapon: "+ equippedWeapon.getName());
        for(int i = 0; i < inventory.getMaxCapacity(); i++){
            System.out.printf("[%d] ",i+1);
            if (items == null || i >= items.length) {
                System.out.print("-EMPTY-");
            } else if (items[i] == null) {
                System.out.print("-EMPTY-");
            } else {
                System.out.print(items[i].getQuantity() + "x " + items[i].getName());
            }
            System.out.println();
        }
    }
    public void displayInventoryInShop(){
        inventory.cleanInventory();
        Item[] items = inventory.getItems();
        System.out.println("========== "+name+"'s INVENTORY ==========");
        System.out.println("CURRENCY: "+currency);
        System.out.println("\nWeapon: "+ equippedWeapon.getName());
        for(int i = 0; i < inventory.getMaxCapacity(); i++){
            System.out.printf("[%d] ",i+1);
            if (items == null || i >= items.length) {
                System.out.print("-EMPTY-");
            } else if (items[i] == null) {
                System.out.print("-EMPTY-");
            } else {
                System.out.print(items[i].getQuantity() + "x " + items[i].getName() + " $"+(items[i].getValue() - (int) (items[i].getValue() * 0.25)));
            }
            System.out.println();
        }
    }
    public void obtainItem(Item item) {
        Item[] items = inventory.getItems();
        if (!inventory.getIsFull()) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] != null && items[i].getItemId().equals(item.getItemId())) {
                    items[i].setQuantity(items[i].getQuantity() + 1);
                    System.out.println("Item \"" + item.getName() + "\" obtained.");
                    delay(500);
                    return;
                }
            }
            for (int i = 0; i < items.length; i++) {
                if (items[i] == null) {
                    items[i] = item;
                    inventory.setCapacity(inventory.getCapacity() + 1);
                    inventory.setIsFull(inventory.getCapacity() == inventory.getMaxCapacity());
                    System.out.println("NEW ITEM \"" + item.getName() + "\" obtained!");
                    delay(500);
                    return;
                }
            }
        } else {
            System.out.println("Inventory is full!");
        }
    }
    public void buyItem(Item item, int quantity){
        Item[] items = inventory.getItems();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getItemId().equals(item.getItemId())) {
                items[i].setQuantity(items[i].getQuantity() + quantity);
                System.out.println("Purchased "+quantity+"x \"" + item.getName() + "\"!");
                delay(500);
                return;
            }
        }
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = item;
                inventory.setCapacity(inventory.getCapacity() + 1);
                inventory.setIsFull(inventory.getCapacity() == inventory.getMaxCapacity());
                items[i].setQuantity(quantity);
                System.out.println("Purchased NEW ITEM  "+quantity+"x \"" + item.getName() + "\"!");
                delay(500);
                return;
            }
        }
    }

    public boolean isUltimateReady() {
        return ultimateCounter >= maxUltimateCounter;
    }

    public void equipWeapon(Weapon weapon){
        this.equippedWeapon = weapon;
        this.basicAttack = weapon.getBasicAttack();
        this.skillAttack = weapon.getSkillAttack();
        this.ultimateAttack = weapon.getUltimateAttack();
        System.out.println(equippedWeapon.getName()+" equipped!");
    }
    public  void unequipWeapon(){
        System.out.println(equippedWeapon.getName()+" unequipped!");
        this.equippedWeapon = new Weapon();
        this.basicAttack = new UnarmedSkill("Punch", "", 1.0, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        this.skillAttack = new UnarmedSkill("Jab", "", 1.1, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        this.ultimateAttack = new UnarmedSkill("Kick", "", 1.2, 0, DamageType.PHYSICAL, TargetType.SINGLE);
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public void setCurrency(int currency){
        this.currency = currency;
    }
    public int getExperience(){
        return experience;
    }
    public int getEnergy(){
        return energy;
    }
    public int getMaxEnergy(){
        return maxEnergy;
    }
    public Skill getBasicAttack(){
        return basicAttack;
    }
    public Skill getSkillAttack(){
        return skillAttack;
    }
    public Skill getUltimateAttack(){
        return ultimateAttack;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public ClassType getClassType(){
        return classType;
    }
    public int getLevel() {
        return level;
    }
    public int getCurrency(){
        return currency;
    }

    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            System.err.println("Thread was interrupted during sleep.");
        }
    }
}