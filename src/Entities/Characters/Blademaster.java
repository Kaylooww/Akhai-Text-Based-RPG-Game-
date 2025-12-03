package Entities.Characters;

public class Blademaster extends Character{
    public Blademaster(String name) {
        super(name, 250, ClassType.BLADEMASTER, 24, 0, 20, 0.15, 0.15, 22);
        this.level = 1;
    }
//
    @Override
    public int attack(){
        return 0;
    }
}
