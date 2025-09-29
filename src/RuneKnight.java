import java.util.Arrays;

class RuneKnight extends Character{
    public RuneKnight(String name) {
        super(name, 285, Arrays.asList(ClassType.MAGICKNIGHT), 0.3, 0.5, 0.7, 18, 22, 15, 0.10, 0.10, 20);
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
