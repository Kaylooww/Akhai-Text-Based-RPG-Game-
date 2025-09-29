import java.util.Arrays;

class Berserker extends Character{
    public Berserker(String name) {
        super(name, 330, Arrays.asList(ClassType.BERSERKER), 0.3, 0.5, 0.7, 27, 0, 12, 0.08, 0.08, 19);
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
