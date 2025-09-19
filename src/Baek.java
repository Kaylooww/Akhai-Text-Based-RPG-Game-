import java.util.Arrays;

class Baek extends Player {
    public Baek() {
        super("Baek", Arrays.asList(ClassType.KNIGHT, ClassType.MAGIC));
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Baek uses Magic imbued swordsman!");
    }
}
