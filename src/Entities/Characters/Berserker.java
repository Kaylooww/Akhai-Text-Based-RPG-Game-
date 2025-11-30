package Entities.Characters;

public class Berserker extends Character{
    public Berserker(String name) {
        super(name, 330, ClassType.BERSERKER, 22, 0, 15, 0.08, 0.08, 18);
        this.level = 1;
    }
//
    @Override
    public int attack(){
        return 0;
    }
}
