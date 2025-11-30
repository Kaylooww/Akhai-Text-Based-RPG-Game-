package Entities.Characters;

public class Shinobi extends Character{
    public Shinobi(String name) {
        super(name, 245, ClassType.ASSASSIN, 22, 0, 12, 0.10, 0.10, 37);
        this.level = 1;
    }

    @Override
    public int attack(){
        return 0;
    }
}
