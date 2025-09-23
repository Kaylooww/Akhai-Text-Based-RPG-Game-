import java.util.Random;

class FortuneTellerNPC extends NPC {
    public FortuneTellerNPC(String name, String description) {
        super(name, description);
    }

    @Override
    public void interact() {
        System.out.println("Kyle: Let me tell your fortune...");
        String[] fortunes = {
                "You will face great challenges ahead!",
                "A powerful ally will join you soon.",
                "Beware of enemies in desert areas.",
                "Your determination will lead you to victory.",
                "The key to success lies in teamwork."
        };
        Random rand = new Random();
        System.out.println("Kyle: " + fortunes[rand.nextInt(fortunes.length)]);
    }
}
