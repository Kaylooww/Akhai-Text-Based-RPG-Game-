package NPC;

public class GuideNPC extends NPC {
    public GuideNPC(String name, String description) {
        super(name, description);
    }
//
    @Override
    public void interact() {
        System.out.println("Frank: Welcome to Akira! Your journey will be challenging but rewarding.");
        System.out.println("Remember to explore each area thoroughly before moving on.");
    }
}
