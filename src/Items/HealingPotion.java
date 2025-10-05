package Items;

import Entities.Characters.Character;

public class HealingPotion extends Consumable{
    int healingAmount;

    public HealingPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int healingAmount) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(Character player){
        System.out.println("You used item "+name);
        player.setHealth(player.getHealth()+healingAmount);
        quantity--;
    }
    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+quantity+")");
        System.out.println(description);
    }
}
