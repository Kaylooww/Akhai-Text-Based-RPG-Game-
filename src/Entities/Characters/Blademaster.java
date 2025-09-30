package Entities.Characters;

import java.util.Arrays;

public class Blademaster extends Character{
    public Blademaster(String name) {
        super(name, 255, Arrays.asList(ClassType.BLADEMASTER), 0.3, 0.5, 0.7, 23, 0, 15, 0.09, 0.09, 20);
    }
//
    @Override
    public int attack(){
        return 0;
    }
    @Override
    public int useBasicAttack(){
        return (int) basicAttack;
    }
    @Override
    public int useSkillAttack(){
        return (int) skillAttack;
    }
    @Override
    public int useUltimateAttack(){
        return (int) ultimateAttack;
    }
}
