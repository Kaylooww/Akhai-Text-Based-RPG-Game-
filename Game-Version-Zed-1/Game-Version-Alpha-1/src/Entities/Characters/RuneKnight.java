package Entities.Characters;

import java.util.Arrays;

public class RuneKnight extends Character{
    public RuneKnight(String name) {
        super(name, 285, Arrays.asList(ClassType.MAGICKNIGHT), 1.0, 1.4, 2.0, 0, 22, 15, 0.10, 0.10, 20);
    }
//
    @Override
    public int attack(){
        return 0;
    }
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
}
