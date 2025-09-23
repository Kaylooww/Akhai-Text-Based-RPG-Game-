import java.util.Arrays;

class Jamal extends Character {
    public Jamal() {
        //Values are only a placeholder
        super("Jamal", Arrays.asList(ClassType.BERSERKER, ClassType.ARCHER), 0.3, 0.5, 0.7, 20, 20, 50, 50, 50);
    }

    //Skills
    @Override
    public double useBasicAttack() {
        return basicAttack;
    }
    @Override
    public double useSkillAttack(){
        System.out.println("Jama uses Archer's Precision Shot!");
        return skillAttack;
    }
    @Override
    public double useUltimateAttack(){
        return ultimateAttack;
    }
}
