package NPC;

import Entities.Characters.Character;
import Items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ShopNPC extends NPC{
    protected List<Item> shopItems = new ArrayList<>();
    protected List<Item> items;
    public ShopNPC(String name, String description, List<Item> items) {
        super(name, description);
        this.items = items;
    }

    @Override
    public void interact(Character player) {
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
                    browseShop(player);
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
    //TODO unlocks certain weapons in certain chapters -For Zed
    public void stockItems(Character player){
        shopItems.clear();

        if(player.getLevel() >= 50){
            shopItems.add(findItemId("HP004", items));
            shopItems.add(findItemId("EP004", items));
            shopItems.add(findItemId("SHP004", items));
            shopItems.add(findItemId("PDP004", items));
            shopItems.add(findItemId("MDP004", items));
            shopItems.add(findItemId("SP004", items));
            shopItems.add(findItemId("EVP004", items));
        }else if(player.getLevel() >= 30){
            shopItems.add(findItemId("HP003", items));
            shopItems.add(findItemId("EP003", items));
            shopItems.add(findItemId("SHP003", items));
            shopItems.add(findItemId("PDP003", items));
            shopItems.add(findItemId("MDP003", items));
            shopItems.add(findItemId("SP003", items));
            shopItems.add(findItemId("EVP003", items));
        }else if(player.getLevel() >= 20){
            shopItems.add(findItemId("HP002", items));
            shopItems.add(findItemId("EP002", items));
            shopItems.add(findItemId("SHP002", items));
            shopItems.add(findItemId("PDP002", items));
            shopItems.add(findItemId("MDP002", items));
            shopItems.add(findItemId("SP002", items));
            shopItems.add(findItemId("EVP002", items));
        }else{
            shopItems.add(findItemId("HP001", items));
            shopItems.add(findItemId("EP001", items));
            shopItems.add(findItemId("SHP001", items));
            shopItems.add(findItemId("PDP001", items));
            shopItems.add(findItemId("MDP001", items));
            shopItems.add(findItemId("SP001", items));
            shopItems.add(findItemId("EVP001", items));
        }
    }
    public void browseShop(Character player){
        stockItems(player);
        boolean exit = false;
        while(!exit){
            System.out.println("╔════════════════════ SHOP ════════════════════╗");
            displayShop();
            System.out.println("╚══════════════════════════════════════════════╝");

            System.out.println("CURRENCY: "+player.getCurrency());
            System.out.println("\n["+(shopItems.size()+1)+"] Exit");
            int choice = getIntInput("Select item to buy: ", 1, shopItems.size()+1);
            if(choice != shopItems.size()+1){
                Item purchasedItem = shopItems.get(choice-1);
                boolean confirm = false;
                while(!confirm){
                    int quantity = getIntInput("How many would you like to buy? (1-"+purchasedItem.getMaxStack()+"):",1, purchasedItem.getMaxStack());
                    if(player.getCurrency() > quantity * purchasedItem.getValue()){
                        if(player.getInventory().getIsFull()){
                            System.out.println("Inventory is full!");
                            break;
                        }else if(purchasedItem.getQuantity() == purchasedItem.getMaxStack() || quantity + purchasedItem.getQuantity() > purchasedItem.getMaxStack() ){
                            System.out.println("Cannot buy item (Max Stack)!");
                            break;
                        }else{
                            player.buyItem(purchasedItem, quantity);
                            player.setCurrency(player.getCurrency() - quantity * purchasedItem.getValue());
                            confirm = true;
                        }
                    }else{
                        System.out.println("Not enough money.");
                    }
                }
            }else{
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
    public void displayShop(){
        int i = 1;
        for(Item item: shopItems){
            System.out.println("  ["+i+"] "+"$"+item.getValue()+" "+item.getName());
            i++;
        }
    }
    public Item findItemId(String itemId, List<Item> item){
        Item foundItem = items.get(0);
        for(int i = 0; i < item.size(); i++){
            if(itemId.equals(item.get(i).getItemId())) {
                foundItem = item.get(i);
            }
        }
        return foundItem;
    }
}

/*
HEALING POTION
HP001 Lesser Healing Potion
HP002 Healing Potion
HP003 Greater Healing Potion
HP004 Legendary Healing Potion

ENERGY POTION
EP001 Lesser Energy Potion
EP002 Energy Potion
EP003 Greater Energy Potion
EP004 Legendary Energy Potion

SHIELD POTION
SHP001 Lesser Shield Potion
SHP002 Shield Potion
SHP003 Greater Shield Potion
SHP004 Legendary Shield Potion

PHYSICAL POTION
PDP001 Lesser Physical Potion
PDP002 Physical Potion
PDP003 Greater Physical Potion
PDP004 Legendary Physical Potion

MAGIC POTION
MDP001 Lesser Magic Potion
MDP002 Magic Potion
MDP003 Greater Magic Potion
MDP004 Legendary Magic Potion

SPEED POTION
SP001 Lesser Speed Potion
SP002 Speed Potion
SP003 Greater Speed Potion
SP004 Legendary Speed Potion

EVASIVENESS POTION
EVP001 Lesser Evasiveness Potion
EVP002 Evasiveness Potion
EVP003 Greater Evasiveness Potion
EVP004 Legendary Evasiveness Potion
*/
