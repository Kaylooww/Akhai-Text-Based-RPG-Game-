package NPC;

import java.util.Random;

public class FortuneTellerNPC extends NPC {
    public FortuneTellerNPC(String name, String description) {
        super(name, description);
    }

    @Override
    public void interact() {
        System.out.println("\n🔮 " + name + " gazes into their crystal ball...");
        String[] fortunes = {
                "You will face great challenges ahead!",
                "I sense a great power within you, traveler.",
                "The spirits have granted you a gift - the ability to cheat death once.",
                "Your determination will lead you to victory.",
                "When death claims you, you shall be given a choice... To return with half your vitality, or to accept your fate."
        };
        Random rand = new Random();
        System.out.println("Kyle: " + fortunes[rand.nextInt(fortunes.length)]);
    }//
}
