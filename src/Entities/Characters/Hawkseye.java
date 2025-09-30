package Entities.Characters;

import java.util.Arrays;

public class Hawkseye extends Character{
    public Hawkseye(String name) {
        super(name, 245, Arrays.asList(ClassType.HAWKSEYE), 0.3, 0.5, 0.7, 31, 0, 11, 0.10, 0.10, 20);
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
