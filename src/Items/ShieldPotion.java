package Items;

import Entities.Characters.Character;

public class ShieldPotion extends Consumable{
    int shieldAmount;

    public ShieldPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int shieldAmount) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.shieldAmount = shieldAmount;
    }

    @Override
    public void use(Character player){
        System.out.println("You used item "+name);
        player.setShield(player.getShield()+shieldAmount);
        quantity--;
    }
    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+quantity+")");
        System.out.println(description);
    }
}
