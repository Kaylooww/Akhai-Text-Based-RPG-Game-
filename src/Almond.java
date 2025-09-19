import java.util.Arrays;

class Almond extends Player {
    public Almond() {
        super("Almond", Arrays.asList(ClassType.ARCHER, ClassType.SUMMON));
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Almond summons a creature to aid in battle!");
    }
}
