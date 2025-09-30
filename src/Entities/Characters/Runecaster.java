package Entities.Characters;

import java.util.Arrays;

public class Runecaster extends Character{
    public Runecaster(String name) {
        super(name, 225, Arrays.asList(ClassType.MAGE), 0.3, 0.5, 0.7, 0, 37, 9, 0.09, 0.09, 20);
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
