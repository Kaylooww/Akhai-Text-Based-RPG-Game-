import java.util.*;

class JinwooSun extends Character {
    public JinwooSun(){
        //Values are only a placeholder
        super("Jinwoo Sun", 100, Arrays.asList(ClassType.values()), 0.3, 0.5, 0.7, 30, 30, 20, 20, 50, 99);
    }
//
    //Skills
    @Override
    public int attack(){return 0;}

    @Override
    public int useBasicAttack() {
        System.out.println("Jinwoo used Basic Attack.");
        return (int) basicAttack;
    }

    @Override
    public int useSkillAttack() {
        System.out.println("Jinwoo used Skill Attack.");
        return (int) skillAttack;
    }

    @Override
    public int useUltimateAttack(){
        System.out.println("Jinwoo Sun shouts ARISE!");
        return (int) ultimateAttack;
    }
}
