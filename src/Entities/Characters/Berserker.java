package Entities.Characters;

import Skills.UnarmedSkill;

import java.util.Arrays;

public class Berserker extends Character{
    public Berserker(String name) {
        super(name, 330, ClassType.BERSERKER, 27, 0, 12, 0.08, 0.08, 19);
    }
//
    @Override
    public int attack(){
        return 0;
    }
    /*
    @Override
    public int useBasicAttack(){
        double basicDamage = basicAttack * physicalDamage;
        return (int) basicDamage;
    }
    @Override
    public int useSkillAttack(){
        double skillDamage = skillAttack * physicalDamage;
        return (int) skillDamage;
    }
    @Override
    public int useUltimateAttack(){
        double ultDamage = ultimateAttack * physicalDamage;
        return (int) ultDamage;
    }
     */
}
