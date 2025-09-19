import java.util.Arrays;

class Jamal extends Player {
    public Jamal() {
        super("Jamal", Arrays.asList(ClassType.BERSERKER, ClassType.ARCHER));
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Jama uses Archer's Precision Shot!");
        // Special archer ability implementation
    }
}
