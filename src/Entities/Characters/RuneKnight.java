package Entities.Characters;

public class RuneKnight extends Character{
    public RuneKnight(String name) {
        super(name, 285, ClassType.RUNEKNIGHT, 10, 22, 20, 0.12, 0.12, 22);
        this.level = 1;
    }
//
    @Override
    public int attack(){
        return 0;
    }
}
