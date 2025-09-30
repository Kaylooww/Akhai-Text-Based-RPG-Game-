import java.util.Arrays;

class Shinobi extends Character{
    public Shinobi(String name) {
        super(name, 245, Arrays.asList(ClassType.ASSASSIN), 0.3, 0.5, 0.7, 25, 0, 10, 0.10, 0.10, 35);
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
