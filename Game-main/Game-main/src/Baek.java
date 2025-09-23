import java.util.Arrays;

class Baek extends Character {
    public Baek() {
        //Values are only a placeholder
        super("Baek", Arrays.asList(ClassType.KNIGHT, ClassType.MAGIC),  0.3, 0.5, 0.7, 20, 20, 50, 50, 50);
    }

    //Skills
    @Override
    public double useBasicAttack() {
        return basicAttack;
    }
    @Override
    public double useSkillAttack(){
        System.out.println("Baek uses Magic imbued swordsman!");
        return skillAttack;
    }
    @Override
    public double useUltimateAttack(){
        return ultimateAttack;
    }
}
