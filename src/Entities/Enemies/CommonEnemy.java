package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;

public class CommonEnemy extends Enemy {
    public EnemySkill basicAttack;

    public CommonEnemy(String name, int health, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    @Override
    public int attack() {
        return performBasicAttack();
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

    public static class Hound extends CommonEnemy {
        public Hound() {
            super("Hound", 100, 32, 32, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Savage Bite", "The hound lunges and bites fiercely", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Slime extends CommonEnemy {
        public Slime() {
            super("Slime", 150, 30, 30, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Acidic Splash", "The slime launches a corrosive glob", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Goblin extends CommonEnemy {
        public Goblin() {
            super("Goblin", 100, 31, 31, 6, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Crude Strike", "The goblin attacks with a poorly-made weapon", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    // For more Common Enemy add here:
    public static class Chimera extends CommonEnemy {
        public Chimera() {
            super("Chimera", 100, 32, 32, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Multi-headed Assault", "The chimera attacks with multiple heads simultaneously", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Wasp extends CommonEnemy {
        public Wasp() {
            super("Wasp", 150, 30, 30, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Venomous Sting", "The wasp strikes with its poisonous stinger", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Thief extends CommonEnemy {
        public Thief() {
            super("Thief", 100, 31, 31, 6, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Dagger Slash", "A quick, precise strike with a concealed blade", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Sludge extends CommonEnemy {
        public Sludge() {
            super("Sludge", 100, 32, 32, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Toxic Ooze", "The sludge releases a wave of poisonous muck", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Orc extends CommonEnemy {
        public Orc() {
            super("Orc", 150, 30, 30, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Brutal Smash", "The orc delivers a powerful, crushing blow", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Demon extends CommonEnemy {
        public Demon() {
            super("Demon", 100, 31, 31, 6, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Infernal Claw", "The demon attacks with fiery, sharp claws", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Frank extends CommonEnemy {
        public Frank() {
            super("Frank", 100, 31, 31, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Dailies", "Frank asks you to do his dailies!", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
}