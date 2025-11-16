package NPC;

import java.util.List;
import java.util.Random;
import Entities.Characters.Character;
import Items.Item;

public class FortuneTellerNPC extends ShopNPC {
    public FortuneTellerNPC(String name, String description, List<Item> items) {
        super(name, description, items);
    }

    @Override
    public void interact(Character player, int currentChapter) {
        boolean exit = false;
        while(!exit){
            System.out.println(name+": Hello fellow adventurer! Care to browse my wares? Or perhaps interested in fortune telling?");
            System.out.println("[1] Browse Shop");
            System.out.println("[2] Sell Item");
            System.out.println("[3] Fortune Telling");
            System.out.println("[4] Exit");
            int choice = getIntInput("Enter choice: ", 1, 4);

            switch (choice) {
                case 1:
                    browseShop(player, currentChapter);
                    break;
                case 2:
                    sellItem(player);
                    break;
                case 3:
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
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }
}
