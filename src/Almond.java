import java.util.Arrays;

class Almond extends Character {
    public Almond() {
        //Values are only a placeholder
        super("Almond", Arrays.asList(ClassType.ARCHER, ClassType.SUMMON), 0.3, 0.5, 0.7, 20, 20, 50, 50, 50);
    }

    //Skills
    @Override
    public int useBasicAttack() {
        return (int) basicAttack;
    }
    @Override
    public int useSkillAttack(){
        System.out.println("Almond summons a creature to aid in battle!");
        return (int) skillAttack;
    }
    @Override
    public int useUltimateAttack(){
        return (int) ultimateAttack;
    }
}
