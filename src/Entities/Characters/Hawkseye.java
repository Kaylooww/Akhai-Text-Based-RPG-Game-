package Entities.Characters;

public class Hawkseye extends Character{
    public Hawkseye(String name) {
        super(name, 245, ClassType.HAWKSEYE, 31, 0, 14, 0.10, 0.10, 20);
        this.level = 1;
    }

    @Override
    public int attack(){
        return 0;
    }
}
