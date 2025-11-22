package Items;

import Entities.Characters.Character;
import StatusEffects.MagicalDamageBoost;
import StatusEffects.SpeedBoost;

public class SpeedPotion extends Consumable{
    int speedAmount;

    public SpeedPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int speedAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.speedAmount = speedAmount;
    }

    @Override
    public void use(Character player){
        if(!hasConsumed){
            SpeedBoost effect = new SpeedBoost(speedAmount);
            effect.applyEffect(player);
            player.addStatusEffect(effect);
            System.out.println("⚡ " + player.getName() + "'s speed increased by " + speedAmount + "!");
            setQuantity(getQuantity() - 1);
            hasConsumed = true;
        }else{
            System.out.println("You can only consume this potion once!");
        }
    }

    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println("\"Increases speed by " + speedAmount + ". Can only be used once before/during battle\"");
    }
}