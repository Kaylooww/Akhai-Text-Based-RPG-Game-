package Entities.Enemies;

import Entities.Characters.DamageType;
import Entities.Characters.TargetType;
import Skills.EnemySkill;
import Skills.Skill;

public class Boss extends Enemy{
    int phase = 1;
    int maxPhase;
    Skill basicAttack;
    Skill skillAttack;
    Skill ultimateAttack;

    public Boss(String name, int health, int maxPhase, int attackDamage, int magicDamage, int defense, double physicalResistance, double magicResistance, int speed){
        super(name, health, attackDamage, magicDamage, defense, physicalResistance, magicResistance, speed);
        this.maxPhase = maxPhase;
    }

    @Override
    public int takeDamage(double damage, int defense, double physicalResistance, double magicResistance) {

        //physical damage type
        double actualDamage = damage * (1 - physicalResistance) - defense; // Simple damage reduction
        if (actualDamage < 1) actualDamage = 1; // Minimum 1 damage

        //magic damage type
        /*double actualDamage = damage * (1 - magicResistance) - defense;
        if (actualDamage < 1) actualDamage = 1;*/

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

    //TODO Explicitly add a status effect for the skill whichever you want(refer to the Skill Class) -For Zed
    //TODO Adjusts the stats -For Zed
    public static class DemonKingDin extends Boss{
        public DemonKingDin(){
            super("Demon-King Din", 30, 2, 20, 20, 10, 0.20, 0.20, 30);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Insert Skill Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Insert Ultimate Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    public static class Abaddon extends Boss{
        public Abaddon(){
            super("Abaddon", 30, 2, 20, 20, 10, 0.20, 0.20, 30);
            basicAttack = new EnemySkill("Insert Basic Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            skillAttack = new EnemySkill("Insert Skill Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
            ultimateAttack = new EnemySkill("Insert Ultimate Attack Name", "Insert Description", 0.20, 0, DamageType.PHYSICAL, TargetType.SINGLE);
        }
    }
    //For more Boss add here:
}

