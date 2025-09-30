package Entities.Characters;

import java.util.Arrays;

public class Berserker extends Character{
    public Berserker(String name) {
        super(name, 330, Arrays.asList(ClassType.BERSERKER), 1.0, 1.4, 2.0, 27, 0, 12, 0.08, 0.08, 19);
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
