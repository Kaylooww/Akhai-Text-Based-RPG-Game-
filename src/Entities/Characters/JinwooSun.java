package Entities.Characters;

import java.util.*;

public class JinwooSun extends Character {
    public JinwooSun(){
        //Values are only a placeholder
        super("Jinwoo Sun", 4000, ClassType.SUNJINWOO, 810, 810, 490, 0.38, 0.38, 190);
        this.level = 999;
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
