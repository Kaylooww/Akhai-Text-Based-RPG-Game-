import java.util.Arrays;

class JinwooSun extends Player {
    public JinwooSun() {
        super("Jinwoo Sun", Arrays.asList(ClassType.values()));
    }

    @Override
    public void useSpecialAbility() {
        System.out.println("Jinwoo Sun shouts ARISE!");
    }
}
