import java.util.Arrays;

class Jamal extends Character {
    public Jamal() {
        //Values are only a placeholder
        super("Jamal", Arrays.asList(ClassType.BERSERKER, ClassType.ARCHER), 0.3, 0.5, 0.7, 30, 30, 20, 20, 50);
    }

    //Skills
    @Override
    public int useBasicAttack() {
        return (int) basicAttack;
    }
    @Override
    public int useSkillAttack(){
        System.out.println("Jamal uses Archer's Precision Shot!");
        return (int) skillAttack;
    }
    @Override
    public int useUltimateAttack(){
        return (int) ultimateAttack;
    }
}
