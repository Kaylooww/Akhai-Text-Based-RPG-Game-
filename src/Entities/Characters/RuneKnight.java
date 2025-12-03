package Entities.Characters;

public class RuneKnight extends Character{
    public RuneKnight(String name) {
        super(name, 290, ClassType.RUNEKNIGHT, 10, 21, 20, 0.12, 0.12, 24);
        this.level = 1;
    }
//
    @Override
    public int attack(){
        return 0;
    }
}
