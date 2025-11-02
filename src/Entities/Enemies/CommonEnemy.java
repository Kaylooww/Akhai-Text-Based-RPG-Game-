package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;
import Skills.Skill;

public class CommonEnemy extends Enemy{
    Skill basicAttack;

    public CommonEnemy(String name, int health, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
    }

    //TODO Explicitly add a status effect for the skill whichever you want(refer to the Skill Class) -For Zed
    //TODO Adjusts the stats -For Zed
    public static class Gooner extends  CommonEnemy{
        public Gooner(){
            super("Gooner",  100, 10, 10, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    public static class Ragebaiter extends  CommonEnemy{
        public Ragebaiter(){
            super("Ragebaiter",  30, 10, 10, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    public static class YourHB extends  CommonEnemy{
        public YourHB(){
            super("Home Boy",  30, 10, 10, 5, 0.05, 0.05, 10);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    //For more Common Enemy add here:
}
