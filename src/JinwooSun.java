import java.util.*;

class JinwooSun extends Character {
    public JinwooSun(){
        //Values are only a placeholder
        super("Jinwoo Sun", Arrays.asList(ClassType.values()), 0.3, 0.5, 0.7, 30, 30, 20, 20, 50);
        }

    //Skills
    @Override
    public int useBasicAttack() {
        return (int) basicAttack;
    }
    @Override
    public int useSkillAttack(){
        return (int) skillAttack;
    }
    @Override
    public int useUltimateAttack(){
        System.out.println("Jinwoo Sun shouts ARISE!");
        return (int) ultimateAttack;
    }
}
