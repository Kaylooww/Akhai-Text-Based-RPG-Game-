package Entities.Characters;

public class Blademaster extends Character{
    public Blademaster(String name) {
        super(name, 255, ClassType.BLADEMASTER, 23, 0, 20, 0.15, 0.15, 24);
        this.level = 1;
    }
//
    @Override
    public int attack(){
        return 0;
    }
}
