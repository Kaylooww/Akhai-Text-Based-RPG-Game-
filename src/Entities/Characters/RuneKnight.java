package Entities.Characters;

public class RuneKnight extends Character{
    public RuneKnight(String name) {
        super(name, 285, ClassType.RUNEKNIGHT, 0, 22, 15, 0.10, 0.10, 20);
        this.level = 1;
    }
//
    @Override
    public int attack(){
        return 0;
    }
    /*
    @Override
    public int useBasicAttack(){
        double basicDamage = basicAttack * magicDamage;
        return (int) basicDamage;
    }
    @Override
    public int useSkillAttack(){
        double skillDamage = skillAttack * magicDamage;
        return (int) skillDamage;
    }
    @Override
    public int useUltimateAttack(){
        double ultDamage = ultimateAttack * magicDamage;
        return (int) ultDamage;
    }
     */
}
