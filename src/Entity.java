abstract class Entity {
    protected String name;
    protected int health;
    protected int attackDamage;
    protected int magicDamage;
    protected int defense;
    protected int physicalResistance;
    protected int magicResistance;
    protected int speed;

    public Entity(String name, int health, int attackDamage, int magicDamage, int defense, int magicResistance, int speed) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.magicDamage = magicDamage;
        this.defense = defense;
        this.magicResistance = magicResistance;
        this.speed = speed;
    }

    //This method is mostly used for the Enemy class
    public abstract int attack();

    public int takeDamage(double damage) {
        double actualDamage = damage;
        health -= (int) actualDamage;
        if (health <= 0) health = 0;
        return (int) actualDamage;
    }

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
}
