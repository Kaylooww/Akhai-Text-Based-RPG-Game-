abstract class Entity {
    protected String name;
    protected int health;
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

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
}
