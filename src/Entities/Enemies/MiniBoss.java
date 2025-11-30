package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;

public class MiniBoss extends Boss {
    public MiniBoss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, maxPhase, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    //TODO dili siya ma kuan walay progress mawa
    public static class EdgeLordZedjy extends MiniBoss {
        public EdgeLordZedjy(){
            super("Edge-Lord Zedjy", 700, 2, 72, 72, 22, 0.26, 0.26, 19);
            basicAttack = new EnemySkill("Backburner", "Backup ka lang", 1.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Second Option", "Never ka naging priority", 1.50, 40, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("NANDITO AKO!", "Nandyan ka pero iba yung gusto nya.", 2.00, 80, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    //For more Miniboss add here:
}