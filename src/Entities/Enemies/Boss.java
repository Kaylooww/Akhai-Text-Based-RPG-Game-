package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;

public class Boss extends Enemy {
    int phase = 1;
    int maxPhase;
    public EnemySkill basicAttack;
    public EnemySkill skillAttack;
    public EnemySkill ultimateAttack;

    public Boss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
        this.maxPhase = maxPhase;
    }

    @Override
    public int attack() {
        // Bosses have more complex attack patterns based on phase and health
        if (getHealth() < getMaxHealth() * 0.15) {
            // Low health - use ultimate
            return performUltimateAttack();
        } else if (Math.random() < 0.3) {
            // 30% chance to use skill attack
            return performSkillAttack();
        } else if (Math.random() < 0.1) {
            // 10% chance to use ultimate
            return performUltimateAttack();
        } else {
            // Default to basic attack
            return performBasicAttack();
        }
    }

    public int performBasicAttack() {
        if (basicAttack != null) {
            double damage = getPhysicalDamage() * basicAttack.getDamageMultiplier();
            System.out.println(name + " uses " + basicAttack.getName() + "!");
            System.out.println(basicAttack.getDescription());
            return (int) damage;
        }
        return getPhysicalDamage();
    }

    public int performSkillAttack() {
        if (skillAttack != null) {
            double damage = getPhysicalDamage() * skillAttack.getDamageMultiplier();
            System.out.println(name + " uses " + skillAttack.getName() + "!");
            System.out.println(skillAttack.getDescription());
            return (int) damage;
        }
        return getPhysicalDamage();
    }

    public int performUltimateAttack() {
        if (ultimateAttack != null) {
            double damage = getPhysicalDamage() * ultimateAttack.getDamageMultiplier();
            System.out.println(name + " uses " + ultimateAttack.getName() + "!");
            System.out.println(ultimateAttack.getDescription());
            return (int) damage;
        }
        return getPhysicalDamage();
    }

    @Override
    public int takeDamage(double damage, int defense, double physicalResistance, double magicResistance) {
        double actualDamage = damage * (1 - physicalResistance) - defense;
        if (actualDamage < 1) actualDamage = 1;

        int oldHealth = health;
        health -= (int) actualDamage;

        if (health <= 0) {
            if(phase < maxPhase){
                phase++;
                health = maxHealth;
                System.out.println("BOSS "+name+" is entering phase "+phase+"!");
            }else{
                health = 0;
                System.out.println("🎯 " + name + " has been defeated!");
            }
        } else if (health < oldHealth * 0.3) {
            System.out.println("⚠️  " + name + " is looking weak!");
        }

        return (int) actualDamage;
    }

    //final boss
    public static class DemonKingDin extends Boss {
        public DemonKingDin() {
            super("Demon-King Din", 1000, 2, 75, 75, 30, 0.25, 0.25, 20);
            basicAttack = new EnemySkill("Infernal Strike", "The Demon-King delivers a searing blow wreathed in hellfire", 1.30, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Abyssal Torment", "Unleashes waves of pure demonic energy that scorch the soul", 1.80, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Ult: Realm of Suffering", "Creates a domain of absolute darkness where pain becomes reality", 2.50, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    // chap 4
    public static class Abaddon extends Boss {
        public Abaddon() {
            super("Abaddon", 800, 2, 70, 70, 28, 0.23, 0.23, 19);
            basicAttack = new EnemySkill("Soul Reaver", "A blade strike that seeks to sever the very essence of life", 1.30, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Plague Wind", "Releases a corrosive gust that decays all it touches", 1.80, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Ult: Apocalypse Call", "Summons the end of days in a cataclysmic explosion of power", 2.50, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    //For more Boss add here:
    // Weak bosses chap 1-3, difficulty spike at chap 4
    //chap 3
    public static class Kamish extends Boss {
        public Kamish() {
            super("Kamish", 500, 2, 60, 60, 17, 0.21, 0.21, 18);
            basicAttack = new EnemySkill("Dragon's Claw", "A swift, precise strike with razor-sharp talons", 1.30, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Ancient Roar", "A thunderous cry that shakes the very foundations of the earth", 1.70, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Ult: Skyfall Descent", "Plummets from the heavens with the force of a meteor", 2.30, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    // chap 2
    public static class EnderDragon extends Boss {
        public EnderDragon() {
            super("Ender Dragon", 400, 2, 45, 45, 16, 0.17, 0.17, 17);
            basicAttack = new EnemySkill("Void Bite", "A chilling attack that seems to consume the light around it", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Ender Breath", "Exhales a cloud of pure void energy that corroses reality", 1.45, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Ult: Dragon's Dive", "A devastating aerial assault that strikes with immense force", 2.00, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    // chap 1
    public static class EnderMan extends Boss {
        public EnderMan() {
            super("Ender Man", 380, 2, 40, 40, 15, 0.16, 0.16, 16);
            basicAttack = new EnemySkill("Telekinetic Slam", "Uses psychic power to strike from an unexpected angle", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Void Warp", "Instantly teleports behind the target for a surprise attack", 1.40, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Ult: Enderman's Wrath", "Unleashes the full power of the void in a chaotic burst", 1.90, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
}