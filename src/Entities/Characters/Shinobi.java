package Entities.Characters;

import java.util.Arrays;

public class Shinobi extends Character{
    public Shinobi(String name) {
        super(name, 245, ClassType.ASSASSIN, 25, 0, 10, 0.10, 0.10, 35);
        this.level = 1;
    }

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
