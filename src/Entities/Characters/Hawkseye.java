package Entities.Characters;

import java.util.Arrays;

public class Hawkseye extends Character{
    public Hawkseye(String name) {
        super(name, 245, ClassType.HAWKSEYE, 31, 0, 11, 0.10, 0.10, 20);
        this.level = 1;
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
