package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;

public class MiniBoss extends Boss{
    public MiniBoss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, maxPhase, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    //TODO Explicitly add a status effect for the skill whichever you want(refer to the Skill Class) -For Zed
    //TODO Adjusts the stats -For Zed
    public static class EdgeLordZedjy extends MiniBoss{
        public EdgeLordZedjy(){
            super("Edge-Lord Zedjy", 30, 3, 22, 22, 11, 0.22, 0.22, 32);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Insert Skill Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Insert Ultimate Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    //For more Miniboss add here:
}
