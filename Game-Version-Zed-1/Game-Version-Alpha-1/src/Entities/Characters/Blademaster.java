package Entities.Characters;

import java.util.Arrays;

public class Blademaster extends Character{
    public Blademaster(String name) {
        super(name, 255, Arrays.asList(ClassType.BLADEMASTER), 1.0, 1.4, 2.0, 23, 0, 15, 0.09, 0.09, 20);
    }
//
    @Override
    public int attack(){
        return 0;
    }
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
}
