package NPC;

import Entities.Characters.*;
import Entities.Characters.Character;
import Items.Item;
import Items.Weapons.Weapon;
import TextFormat.ColorUtil;

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
    public void interact(Character player, int currentChapter) {
        boolean exit = false;
        while(!exit){
            System.out.println(ColorUtil.purple("\n"+name+": Hello fellow adventurer! Care to browse my wares? Or perhaps interested in fortune telling?"));
            System.out.println(ColorUtil.brightPurpleBold("╔════════════════════════════════════════════════════╗"));
            System.out.println(ColorUtil.brightPurpleBold("║") + ColorUtil.brightCyanBold("                    KYLE'S SHOP                     ") + ColorUtil.brightPurpleBold("║"));
            System.out.println(ColorUtil.brightPurpleBold("╠════════════════════════════════════════════════════╣"));
            System.out.println(ColorUtil.brightPurpleBold("║  ") + ColorUtil.brightCyanBold("[1] Browse Shop") + ColorUtil.brightPurpleBold("                                   ║"));
            System.out.println(ColorUtil.brightPurpleBold("║  ") + ColorUtil.brightCyanBold("[2] Sell Item") + ColorUtil.brightPurpleBold("                                     ║"));
            System.out.println(ColorUtil.brightPurpleBold("║  ") + ColorUtil.brightCyanBold("[3] Fortune Telling") + ColorUtil.brightPurpleBold("                               ║"));
            System.out.println(ColorUtil.brightPurpleBold("║  ") + ColorUtil.brightCyanBold("[4] Exit ") + ColorUtil.brightPurpleBold("                                         ║"));
            System.out.println(ColorUtil.brightPurpleBold("╚════════════════════════════════════════════════════╝"));
            int choice = getIntInput("Enter choice: ", 1, 4);

            switch (choice) {
                case 1:
                    browseShop(player, currentChapter);
                    break;
                case 2:
                    sellItem(player);
                    break;
                case 3:
                    System.out.println(ColorUtil.blue("\n🔮 " + name + " gazes into their crystal ball..."));
                    String[] fortunes = {
                            "You will face great challenges ahead!",
                            "I sense a great power within you, traveler.",
                            "The spirits have granted you a gift - the ability to cheat death once.",
                            "Your determination will lead you to victory.",
                            "When death claims you, you shall be given a choice... To return with half your vitality, or to accept your fate."
                    };
                    Random rand = new Random();
                    System.out.println(ColorUtil.brightPurpleBold("Kyle The Fortune Teller: " + fortunes[rand.nextInt(fortunes.length)]));
                    break;
                case 4:
                    exit = true;
                    break;
            }
        }
    }

    public void stockItems(Character player, int currentChapter){
        shopItems.clear();

        if(currentChapter >= 5){
            shopItems.add(findItemId("HP004", items));
            shopItems.add(findItemId("EP004", items));
            shopItems.add(findItemId("PDP004", items));
            shopItems.add(findItemId("MDP004", items));
            shopItems.add(findItemId("SP004", items));
            shopItems.add(findItemId("EVP004", items));
        }else if(currentChapter >= 3){
            shopItems.add(findItemId("HP003", items));
            shopItems.add(findItemId("EP003", items));
            shopItems.add(findItemId("PDP003", items));
            shopItems.add(findItemId("MDP003", items));
            shopItems.add(findItemId("SP003", items));
            shopItems.add(findItemId("EVP003", items));
        }else if(currentChapter == 2){
            shopItems.add(findItemId("HP002", items));
            shopItems.add(findItemId("EP002", items));
            shopItems.add(findItemId("PDP002", items));
            shopItems.add(findItemId("MDP002", items));
            shopItems.add(findItemId("SP002", items));
            shopItems.add(findItemId("EVP002", items));
        }else{
            shopItems.add(findItemId("HP001", items));
            shopItems.add(findItemId("EP001", items));
            shopItems.add(findItemId("PDP001", items));
            shopItems.add(findItemId("MDP001", items));
            shopItems.add(findItemId("SP001", items));
            shopItems.add(findItemId("EVP001", items));
        }

        addWeapons(player, currentChapter);
    }
    public void addWeapons(Character player, int currentChapter){
        if(player instanceof Berserker || player instanceof JinwooSun){
            if(currentChapter >= 5){
                shopItems.add(findItemId("BS004.1", items));
                shopItems.add(findItemId("BS004.2", items));
                shopItems.add(findItemId("BS004.3", items));
            }else if(currentChapter >= 3){
                shopItems.add(findItemId("BS003.1", items));
                shopItems.add(findItemId("BS003.2", items));
                shopItems.add(findItemId("BS003.3", items));
            }else if(currentChapter == 2){
                shopItems.add(findItemId("BS002.1", items));
                shopItems.add(findItemId("BS002.2", items));
                shopItems.add(findItemId("BS002.3", items));
            }else{
                shopItems.add(findItemId("BS001.1", items));
                shopItems.add(findItemId("BS001.2", items));
            }
        }else if(player instanceof Blademaster || player instanceof JinwooSun){
            if(currentChapter >= 5){
                shopItems.add(findItemId("SW004.1", items));
                shopItems.add(findItemId("SW004.2", items));
                shopItems.add(findItemId("SW004.3", items));
            }else if(currentChapter >= 3){
                shopItems.add(findItemId("SW003.1", items));
                shopItems.add(findItemId("SW003.2", items));
                shopItems.add(findItemId("SW003.3", items));
            }else if(currentChapter == 2){
                shopItems.add(findItemId("SW002.1", items));
                shopItems.add(findItemId("SW002.2", items));
                shopItems.add(findItemId("SW002.3", items));
            }else{
                shopItems.add(findItemId("SW001.1", items));
                shopItems.add(findItemId("SW001.2", items));
            }
        }else if(player instanceof Hawkseye || player instanceof JinwooSun){
            if(currentChapter >= 5){
                shopItems.add(findItemId("BW004.1", items));
                shopItems.add(findItemId("BW004.2", items));
                shopItems.add(findItemId("BW004.3", items));
            }else if(currentChapter >= 3){
                shopItems.add(findItemId("BW003.1", items));
                shopItems.add(findItemId("BW003.2", items));
                shopItems.add(findItemId("BW003.3", items));
            }else if(currentChapter == 2){
                shopItems.add(findItemId("BW002.1", items));
                shopItems.add(findItemId("BW002.2", items));
                shopItems.add(findItemId("BW002.3", items));
            }else{
                shopItems.add(findItemId("BW001.1", items));
                shopItems.add(findItemId("BW001.2", items));
            }
        }else if(player instanceof Runecaster || player instanceof JinwooSun){
            if(currentChapter >= 5){
                shopItems.add(findItemId("MGS004.1", items));
                shopItems.add(findItemId("MGS004.2", items));
                shopItems.add(findItemId("MGS004.3", items));
            }else if(currentChapter >= 3){
                shopItems.add(findItemId("MGS003.1", items));
                shopItems.add(findItemId("MGS003.2", items));
                shopItems.add(findItemId("MGS003.3", items));
            }else if(currentChapter == 2){
                shopItems.add(findItemId("MGS002.1", items));
                shopItems.add(findItemId("MGS002.2", items));
                shopItems.add(findItemId("MGS002.3", items));
            }else{
                shopItems.add(findItemId("MGS001.1", items));
                shopItems.add(findItemId("MGS001.2", items));
            }
        }else if(player instanceof RuneKnight || player instanceof JinwooSun){
            if(currentChapter >= 5){
                shopItems.add(findItemId("MGSW004.1", items));
                shopItems.add(findItemId("MGSW004.2", items));
                shopItems.add(findItemId("MGSW004.3", items));
            }else if(currentChapter >= 3){
                shopItems.add(findItemId("MGSW003.1", items));
                shopItems.add(findItemId("MGSW003.2", items));
                shopItems.add(findItemId("MGSW003.3", items));
            }else if(currentChapter == 2){
                shopItems.add(findItemId("MGSW002.1", items));
                shopItems.add(findItemId("MGSW002.2", items));
                shopItems.add(findItemId("MGSW002.3", items));
            }else{
                shopItems.add(findItemId("MGSW001.1", items));
                shopItems.add(findItemId("MGSW001.2", items));
            }
        }else if(player instanceof Shinobi || player instanceof JinwooSun){
            if(currentChapter >= 5){
                shopItems.add(findItemId("DR004.1", items));
                shopItems.add(findItemId("DR004.2", items));
                shopItems.add(findItemId("DR004.3", items));
            }else if(currentChapter >= 3){
                shopItems.add(findItemId("DR003.1", items));
                shopItems.add(findItemId("DR003.2", items));
                shopItems.add(findItemId("DR003.3", items));
            }else if(currentChapter == 2){
                shopItems.add(findItemId("DR002.1", items));
                shopItems.add(findItemId("DR002.2", items));
                shopItems.add(findItemId("DR002.3", items));
            }else{
                shopItems.add(findItemId("DR001.1", items));
                shopItems.add(findItemId("DR001.2", items));
            }
        }
    }
    public void browseShop(Character player, int currentChapter){
        stockItems(player,  currentChapter);
        boolean exit = false;
        while(!exit){
            System.out.println(ColorUtil.brightPurpleBold("╔═══════════════════════ SHOP ═══════════════════════╗"));
            displayShop();
            System.out.println(ColorUtil.brightPurpleBold("╚════════════════════════════════════════════════════╝"));

            System.out.println("CURRENCY: "+player.getCurrency());
            System.out.println(ColorUtil.brightCyanBold("\n["+(shopItems.size()+1)+"] Exit"));
            int choice = getIntInput("Select item to buy: ", 1, shopItems.size()+1);
            if(choice != shopItems.size()+1){
                Item purchasedItem = shopItems.get(choice-1);
                boolean confirm = false;
                while(!confirm){
                    if(player.hasItem(purchasedItem) && purchasedItem instanceof Weapon){
                        System.out.println("You've already owned this weapon!");
                        break;
                    }else if(purchasedItem.getMaxStack() == purchasedItem.getQuantity()){
                        System.out.println("Cannot buy item (Max Stack)!");
                        break;
                    }

                    System.out.println(ColorUtil.brightPurpleBold("════════════════════ BUYING ITEM ═════════════════════"));
                    purchasedItem.displayInfo();
                    int quantity = getIntInput("\nHow many would you like to buy? (0-"+(purchasedItem.getMaxStack() - purchasedItem.getQuantity())+"): ",0, purchasedItem.getMaxStack());
                    if(quantity == 0){
                        break;
                    }
                    if(player.getCurrency() > quantity * purchasedItem.getValue()){
                        if(player.getInventory().getIsFull()){
                            System.out.println("Inventory is full!");
                            break;
                        }else{
                            player.buyItem(purchasedItem, quantity);
                            player.setCurrency(player.getCurrency() - quantity * purchasedItem.getValue());
                            confirm = true;
                        }
                    }else{
                        System.out.println("Not enough money!");
                    }
                }
            }else{
                exit = true;
            }
        }
    }
    public void sellItem(Character player){
        boolean confirm = false;
        boolean isEmpty = false;

        while(!confirm){
            player.displayInventoryInShop();
            System.out.println(ColorUtil.brightCyanBold("[11] Back"));
            int choice = getIntInput("Select item to sell: ", 1, 11);
            if(choice == 11){
                break;
            }
            Item item = player.getInventory().getItems()[--choice];
            if(item == null){
                isEmpty = true;
            }

            if(!isEmpty){
                System.out.println(ColorUtil.brightPurpleBold("════════════════════ SELLING ITEM ════════════════════"));
                if(item.getQuantity() > 1){
                    int quantity = getIntInput(ColorUtil.orange("How many would you like to sell?: "), 0, item.getQuantity());
                    if(quantity != 0){
                        int soldPrice = quantity * (item.getValue() - (int) (item.getValue() * 0.75));
                        player.setCurrency(player.getCurrency() + soldPrice);
                        item.setQuantity(item.getQuantity() - quantity);
                        System.out.println("Sold "+quantity+"x "+item.getName()+" for" + ColorUtil.greenBright(" $"+soldPrice)+"!");
                    }
                }else{
                    choice = getIntInput(ColorUtil.orange("Are you sure you want to sell "+item.getName()+"? (Yes [1] | No [0]): "), 0, 1);
                    if(choice == 1){
                        if(item instanceof Weapon weapon && weapon.getIsEquipped() == true){
                            System.out.println(ColorUtil.redBright("Selected item cannot be sold. Unequip item first."));
                        }else{
                            int soldPrice = item.getValue() - (int) (item.getValue() * 0.75);
                            player.setCurrency(player.getCurrency() + soldPrice);
                            item.setQuantity(item.getQuantity() - 1);
                            System.out.println("Sold "+item.getName()+" for" + ColorUtil.greenBright(" $"+soldPrice)+"!");
                        }
                    }
                }
            }else{
                System.out.println(ColorUtil.redBright("Please select another item."));
                isEmpty = false;
                delay(1000);
            }
        }
    }
    public void displayShop(){
        int i = 1;
        for(Item item: shopItems){
            System.out.println(ColorUtil.brightCyanBold("   ["+i+"] ")+ ColorUtil.greenBright("$"+item.getValue())+" "+item.getName());
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

    public void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Re-interrupt the thread
            System.err.println("Thread was interrupted during sleep.");
        }
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
