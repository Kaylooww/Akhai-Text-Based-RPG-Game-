package Entities.Characters;

import java.util.*;

public class JinwooSun extends Character {
    public JinwooSun(){
        //Values are only a placeholder
        super("Jinwoo Sun", 100, ClassType.SUNJINWOO, 30, 30, 20, 0.50, 0.50, 99);
    }
//
    //Skills
    @Override
    public int attack(){return 0;}
    /*
    @Override
    public int useBasicAttack() {
        double basicDamage = basicAttack * physicalDamage;
        System.out.println("Jinwoo used Basic Attack.");
        return (int) basicDamage;
    }

    @Override
    public int useSkillAttack() {
        double skillDamage = skillAttack * physicalDamage;
        System.out.println("Jinwoo used Skill Attack.");
        return (int) skillDamage;
    }

    @Override
    public int useUltimateAttack(){
        double ultDamage = ultimateAttack * physicalDamage;
        System.out.println("Jinwoo Sun shouts ARISE!");
        return (int) ultDamage;
    }
     */
}
