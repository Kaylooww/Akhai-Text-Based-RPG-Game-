package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;

public class EliteEnemy extends Enemy {
    public EnemySkill basicAttack;
    public EnemySkill skillAttack;

    public EliteEnemy(String name, int health, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    @Override
    public int attack() {
        // Elite enemies can choose between basic and skill attacks
        if (shouldUseSkillAttack()) {
            return performSkillAttack();
        } else {
            return performBasicAttack();
        }
    }

    private boolean shouldUseSkillAttack() {
        // Simple logic: 30% chance to use skill attack
        return Math.random() < 0.3;
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

    public static class PrimordialVishap extends EliteEnemy {
        public PrimordialVishap() {
            super("Primordial Vishap", 250, 50, 50, 10, 0.15, 0.15, 15);
            basicAttack = new EnemySkill("Ancient Claw", "The vishap strikes with claws that have weathered eons", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Stone Shatter", "Unleashes a shockwave that cracks the very earth", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class BathysmalHunter extends EliteEnemy {
        public BathysmalHunter() {
            super("Bathysmal Hunter", 250, 55, 55, 7, 0.12, 0.12, 15);
            basicAttack = new EnemySkill("Abyssal Spear", "A precise thrust from the depths with a crystalline weapon", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Depth Charge", "Releases compressed water energy in a explosive burst", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    //For more Elite Enemy add here:
    public static class AbyssMage extends EliteEnemy {
        public AbyssMage() {
            super("Abyss Mage", 250, 53, 53, 8, 0.14, 0.14, 15);
            basicAttack = new EnemySkill("Dark Bolt", "Conjures and hurls a sphere of corrupt energy", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Cursed Ritual", "Channels abyssal power into a devastating magical strike", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class WildernessExile extends EliteEnemy {
        public WildernessExile() {
            super("Wilderness Exile", 260, 48, 48, 10, 0.15, 0.15, 15);
            basicAttack = new EnemySkill("Desperate Swing", "A wild, powerful attack born from survival instinct", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Feral Onslaught", "Unleashes a rapid series of frenzied strikes", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class ShadowHusk extends EliteEnemy {
        public ShadowHusk() {
            super("Shadow Husk", 260, 53, 53, 7, 0.12, 0.12, 15);
            basicAttack = new EnemySkill("Void Touch", "A chilling strike that drains warmth and light", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Eclipse Slam", "Gathers darkness into a crushing blow of pure shadow", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class RuinGuard extends EliteEnemy {
        public RuinGuard() {
            super("Ruin Guard", 260, 52, 52, 8, 0.13, 0.13, 15);
            basicAttack = new EnemySkill("Mechanical Fist", "A piston-powered punch from ancient machinery", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Rocket Barrage", "Unleashes a volley of guided projectiles", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class AbyssRogue extends EliteEnemy {
        public AbyssRogue() {
            super("AbyssRogue", 270, 46, 46, 10, 0.11, 0.11, 15);
            basicAttack = new EnemySkill("Shadow Stab", "A quick, precise dagger attack from the darkness", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Phantom Strike", "Vanishes and reappears for a devastating backstab", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class MirrorMaiden extends EliteEnemy {
        public MirrorMaiden() {
            super("Mirror Maiden", 270, 51, 51, 7, 0.08, 0.08, 15);
            basicAttack = new EnemySkill("Crystalline Shard", "Launches sharp, reflective fragments at the target", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Reflection's Wrath", "Creates mirror images that attack simultaneously", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class AbyssHerald extends EliteEnemy {
        public AbyssHerald() {
            super("Abyss Herald", 270, 50, 50, 8, 0.09, 0.09, 15);
            basicAttack = new EnemySkill("Herald's Blade", "A sweeping strike imbued with dark authority", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: Abyssal Decree", "Channels the will of the abyss into a crushing blow", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class FrankDaniel extends EliteEnemy {
        public FrankDaniel() {
            super("Frank Daniel", 240, 55, 55, 10, 0.15, 0.15, 15);
            basicAttack = new EnemySkill("Ano jay?", "Ano wala?", 1.10, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Skill: 67", "Yes.", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
}