import java.util.*;

abstract class Character extends Entity {
    protected int energy = 100;
    protected int experience = 0;
    protected int level = 1;
    protected List<ClassType> classes;

    public Character(String name, List<ClassType> classes, int health, int attackDamage, int magicDamage, int armor, int speed, int magicResistance) {
        super(name, health, attackDamage, magicDamage, armor, speed, magicResistance);
        this.classes = classes;
    }

    public abstract void useSpecialAbility();

    public void gainExperience(int exp) {
        experience += exp;
        if (experience >= level * 100) {
            levelUp();
        }
    }

    private void levelUp() {
        experience -= level * 100;
        level++;
        System.out.println(name + " leveled up to level " + level + "!");

        // Improve stats on level up
        health += 20;
        attackDamage += 5;
        armor += 3;
        speed += 2;
        magicResistance += 3;
        magicDamage += 4;
    }

    public void displayStats() {
        System.out.println("\n=== " + name + "'s STATS ===");
        System.out.println("Level: " + level);
        System.out.println("Experience: " + experience + "/" + (level * 100));
        System.out.println("Classes: " + classes);
        System.out.println("HP: " + health);
        System.out.println("Attack: " + attackDamage);
        System.out.println("Armor: " + armor);
        System.out.println("Speed: " + speed);
        System.out.println("Energy: " + energy);
        System.out.println("Magic Defense: " + magicResistance);
        System.out.println("Magic Damage: " + magicDamage);
        System.out.println("====================");
    }

    @Override
    public int attack() {
        Random rand = new Random();
        return attackDamage + rand.nextInt(11) - 5; // Attack with slight variation
    }
}
