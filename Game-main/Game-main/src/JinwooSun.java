import java.util.*;

class JinwooSun extends Character {
    public JinwooSun(){
        //Values are only a placeholder
        super("Jinwoo Sun", Arrays.asList(ClassType.values()), 0.3, 0.5, 0.7, 20, 20, 50, 50, 50);
        }

    //Skills
    @Override
    public double useBasicAttack() {
        return basicAttack;
    }
    @Override
    public double useSkillAttack(){
        return skillAttack;
    }
    @Override
    public double useUltimateAttack(){
        System.out.println("Jinwoo Sun shouts ARISE!");
        return ultimateAttack;
    }
}
