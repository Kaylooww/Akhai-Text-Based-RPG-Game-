package Entities.Characters;

import java.util.Arrays;

public class Runecaster extends Character{
    public Runecaster(String name) {
        super(name, 225, ClassType.MAGE, 0, 37, 9, 0.09, 0.09, 20);
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
        double basicDamage = basicAttack * magicDamage;
        return (int) basicDamage;
    }
    @Override
    public int useSkillAttack(){
        double skillDamage = skillAttack * magicDamage;
        return (int) skillDamage;
    }
    @Override
    public int useUltimateAttack(){
        double ultDamage = ultimateAttack * magicDamage;
        return (int) ultDamage;
    }
     */
}
