import java.util.*;

abstract class Character extends Entity {
    protected int maxHealth; // Track maximum health for resurrection
    protected int maxEnergy;
    protected int energy = 100;
    protected int level = 1;
    protected int experience = 0;
    protected int experienceNeeded = 100;
    protected double basicAttack;
    protected double skillAttack;
    protected double ultimateAttack;
    protected List<ClassType> classes;
    protected boolean hasResurrected = false; // New resurrection flag

    public Character(String name, int health, List<ClassType> classes, double basicAttack, double skillAttack, double ultimateAttack, int physicalDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed) {
        super(name, health, physicalDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
        this.classes = classes;
        this.basicAttack = physicalDamage + (basicAttack * physicalDamage);
        this.skillAttack = physicalDamage + (skillAttack * physicalDamage);
        this.ultimateAttack = physicalDamage + (ultimateAttack * physicalDamage);
        this.maxHealth = health; // Initialize max health
    }

    public abstract int useBasicAttack();
    public abstract int useSkillAttack();
    public abstract int useUltimateAttack();

    public void gainExperience(int exp) {
        experience += exp;
        while (experience >= experienceNeeded && level <= 30) { // Added level cap
            levelUp();
        }

    }
    public void levelUp() {
        experience -= experienceNeeded;
        level++;
        experienceNeeded += 20;
        System.out.println(name + " leveled up to level " + level + "!");

        // Improve stats on level up
        int oldMaxHealth = maxHealth;
        maxHealth += 15;
        health += 15; // Also increase current health
        physicalDamage += 3;
        defense += 2;
        speed += 1;
        magicResistance += 2;
        magicDamage += 3;

        System.out.println("Max HP increased: " + oldMaxHealth + " → " + maxHealth);
    }

    public int getLevel() {
        return level;
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

        // Resurrect with 50% of max health
        health = maxHealth / 2;
        hasResurrected = true;

        System.out.println(name + " resurrects with " + health + " HP!");
        System.out.println("This resurrection has been consumed and cannot be used again.");

        return;
    }

    public boolean hasResurrected() {
        return hasResurrected;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    // Update displayStats to show resurrection status
    public void displayStats() {
        System.out.println("\n=== " + name + "'s STATS ===");
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience + "/" + experienceNeeded);
        System.out.println("Classes: " + classes);
        System.out.println("HP: " + health + "/" + maxHealth);
        System.out.println("Attack: " + physicalDamage);
        System.out.println("Armor: " + defense);
        System.out.println("Speed: " + speed);
        System.out.println("Energy: " + energy);
        System.out.println("Magic Defense: " + magicResistance);
        System.out.println("Magic Damage: " + magicDamage);
        System.out.println("Resurrection: " + (hasResurrected ? "❌ USED" : "✅ AVAILABLE"));
        System.out.println("====================");
    }
}