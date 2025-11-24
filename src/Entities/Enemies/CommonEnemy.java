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

    public static class Gloomfang extends CommonEnemy {
        public Gloomfang() {
            super("Gloomfang", 100, 32, 32, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Shadow Maul", "The beast bites with darkness-infused fangs", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Mireling extends CommonEnemy {
        public Mireling() {
            super("Mireling", 150, 30, 30, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Bog Spit", "The mireling spits murky sludge that slows its target", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class CrossroadSneak extends CommonEnemy {
        public CrossroadSneak() {
            super("Crossroad Sneak", 100, 31, 31, 6, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Back-Alley Jab", "A dirty, sudden stab from the shadows", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    // For more Common Enemy add here:
    public static class WhisperChimera extends CommonEnemy {
        public WhisperChimera() {
            super("Whisper Chimera", 100, 32, 32, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Echoing Rend", "Multiple heads strike, their snarls echo unnaturally", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Mistwing extends CommonEnemy {
        public Mistwing() {
            super("Mistwing", 150, 30, 30, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Piercing Needle", "A sharp thrust darting through drifting mist", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class CrossroadCutpurse extends CommonEnemy {
        public CrossroadCutpurse() {
            super("Crossroad Cutpurse", 100, 31, 31, 6, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Silent Slice", "A clean, fast cut delivered without a sound", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Murkgut extends CommonEnemy {
        public Murkgut() {
            super("Murkgut", 100, 32, 32, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Rotwave", "The creature releases a sickening ripple of decay", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class RoadbreakerBrute extends CommonEnemy {
        public RoadbreakerBrute() {
            super("Roadbreaker Brute", 150, 30, 30, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Earthshatter Blow", "A heavy strike that cracks the ground beneath it", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class CrossroadFiend extends CommonEnemy {
        public CrossroadFiend() {
            super("Crossroad Fiend", 100, 31, 31, 6, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Soulflare Slash", "A burning claw strike that emits eerie whispers", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }

    public static class Frank extends CommonEnemy {
        public Frank() {
            super("Frank", 100, 31, 31, 8, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Dailies", "Frank asks you to do his dailies!", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
}