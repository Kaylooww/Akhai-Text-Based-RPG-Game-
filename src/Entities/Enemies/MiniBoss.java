package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;

public class MiniBoss extends Boss {
    public MiniBoss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, maxPhase, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    public static class EdgeLordZedjy extends MiniBoss {
        public EdgeLordZedjy(){
            //High Hp low resistance
            //super("Edge-Lord Zedjy", 700, 2, 72, 72, 20, 0.22, 0.22, 19);
            //basicAttack = new EnemySkill("Backburner", "Backup ka lang", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            //skillAttack = new EnemySkill("Second Option", "Never ka naging priority", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            //ultimateAttack = new EnemySkill("NANDITO AKO!", "Nandyan ka pero iba yung gusto nya.", 2.00, 80, DamageType.PHYSICAL, TargetType.SINGLE);

            //Experimental adjustment: Low hp high resistance, higher ULT damage, lower damage basic and skill damage
            super("Edge-Lord Zedjy", 200, 2, 85, 85, 1, 0.80, 0.80, 19);
            basicAttack = new EnemySkill("Backburner", "Backup ka lang", 0.70, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("[Skill] Second Option", "Never ka naging priority", 0.90, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("[ULTIMATE] NANDITO AKO!", "Nandyan ka pero iba yung gusto nya.", 2.15, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
}