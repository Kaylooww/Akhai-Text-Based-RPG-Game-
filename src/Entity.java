abstract class Entity {
    protected String name;
    protected int health;
    protected int attackDamage;
    protected int magicDamage;
    protected int armor;
    protected int speed;
    protected int magicResistance;

    public Entity(String name, int health, int attackDamage, int magicDamage, int armor, int speed, int magicResistance) {
        this.name = name;
        this.health = health;
        this.attackDamage = attackDamage;
        this.magicDamage = magicDamage;
        this.armor = armor;
        this.speed = speed;
        this.magicResistance = magicResistance;
    }

    public abstract int attack();

    public int takeDamage(int damage) {
        int actualDamage = Math.max(1, damage - armor / 10);
        health -= actualDamage;
        if (health <= 0) health = 0;
        return actualDamage;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public int getAttack() {
        return attackDamage;
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    public int getArmor() {
        return armor;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMagicResistance() {
        return magicResistance;
    }
}
