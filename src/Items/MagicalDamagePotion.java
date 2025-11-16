package Items;

import Entities.Characters.Character;
import StatusEffects.MagicalDamageBoost;
import StatusEffects.PhysicalDamageBoost;

public class MagicalDamagePotion extends Consumable{
    double magicalDamageAmount;

    public MagicalDamagePotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double magicalDamageAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.magicalDamageAmount = magicalDamageAmount;
    }

    @Override
    public void use(Character player){
        if(hasConsumed == false){
            MagicalDamageBoost effect = new MagicalDamageBoost(magicalDamageAmount);
            effect.applyEffect(player);
            player.addStatusEffect(effect);
            System.out.println("✨ " + player.getName() + "'s magical damage increased by " + (magicalDamageAmount * 100) + "%!");
            setQuantity(getQuantity() - 1);
            hasConsumed = true;
        }else{
            System.out.println("You can only consume this potion once!");
        }
    }

    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println(description);
        System.out.println("Increases magical damage by " + (magicalDamageAmount * 100) + "% for the current battle");
    }
}