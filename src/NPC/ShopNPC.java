package NPC;

import Entities.Characters.Character;
import Items.Item;

public abstract class ShopNPC extends NPC{
    public ShopNPC(String name, String description) {
        super(name, description);
    }

    public void browseShop(Character player){
        boolean exit = false;
        while(!exit){
            System.out.println("╔════════════════════ SHOP ════════════════════╗");
            System.out.println("-Insert items for sale-");
            System.out.println("╚══════════════════════════════════════════════╝");

            System.out.println("CURRENCY: "+player.getCurrency());
            System.out.println("\n[2] Exit");
            int choice = getIntInput("Select item to buy: ", 1, 2);
            switch(choice){
                case 1:
                    System.out.println("Buying item");
                case 2:
                    exit = true;
            }
        }
    }
    public void sellItem(Character player){
        boolean confirm = false;

        while(!confirm){
            player.displayInventoryInShop();
            System.out.println("[11] Back");
            int choice = getIntInput("Select item to sell: ", 1, 11);
            if(choice == 11){
                confirm = true;
                break;
            }

            Item item = player.getInventory().getItems()[--choice];
            System.out.println("========= SELLING ITEM =========");
            if(item.getQuantity() > 1){
                int quantity = getIntInput("How many would you like to sell?: ", 1, item.getQuantity());
                int soldPrice = quantity * (item.getValue() - (int) (item.getValue() * 0.25));
                player.setCurrency(player.getCurrency() + soldPrice);
                item.setQuantity(item.getQuantity() - quantity);
                System.out.println("Sold $"+soldPrice+" worth of items!");
            }else{
                int soldPrice = item.getValue() - (int) (item.getValue() * 0.25);
                player.setCurrency(player.getCurrency() + soldPrice);
                item.setQuantity(item.getQuantity() - 1);
                System.out.println("Sold $"+soldPrice+" worth of item!");
            }
        }
    }
}
