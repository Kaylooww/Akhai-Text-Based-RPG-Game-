package Entities.Characters;

public class Runecaster extends Character{
    public Runecaster(String name) {
        super(name, 205, ClassType.RUNECASTER, 5, 37, 13, 0.09, 0.09, 17);
        this.level = 1;
    }
    //
    @Override
    public int attack(){
        return 0;
    }
}
