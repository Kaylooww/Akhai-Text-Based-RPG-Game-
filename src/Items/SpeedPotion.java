package Items;

import Entities.Characters.Character;
import StatusEffects.SpeedBoost;

public class SpeedPotion extends Consumable{
    int speedAmount;

    public SpeedPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int speedAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.speedAmount = speedAmount;
    }

    @Override
    public void use(Character player){
        SpeedBoost effect = new SpeedBoost(speedAmount);
        effect.applyEffect(player);
        player.addStatusEffect(effect);
        System.out.println("⚡ " + player.getName() + "'s speed increased by " + speedAmount + "!");
        setQuantity(getQuantity() - 1);
    }

    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println(description);
        System.out.println("Increases speed by " + speedAmount + " for the current battle");
    }
}