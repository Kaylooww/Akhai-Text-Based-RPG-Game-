package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;
import Skills.Skill;

public class EliteEnemy extends Enemy{
    Skill basicAttack;
    Skill skillAttack;

    public EliteEnemy(String name, int health, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    //TODO Explicitly add a status effect for the skill whichever you want(refer to the Skill Class) -For Zed
    //TODO Adjusts the stats -For Zed
    public static class MasterBaiter extends  EliteEnemy{
        public MasterBaiter(){
            super("Masterbaiter", 30, 15, 15, 8, 0.15, 0.15, 25);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Insert Skill Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    public static class Redditor extends  EliteEnemy{
        public Redditor(){
            super("Redditor", 30, 15, 15, 8, 0.15, 0.15, 25);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Insert Skill Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    //For more Elite Enemy add here:
}
