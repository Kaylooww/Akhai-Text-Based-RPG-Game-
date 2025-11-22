package Items;

import Entities.Characters.Character;
import StatusEffects.PhysicalDamageBoost;

import java.text.DecimalFormat;

public class PhysicalDamagePotion extends Consumable{
    double physicalDamageAmount;

    public PhysicalDamagePotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double physicalDamageAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.physicalDamageAmount = physicalDamageAmount;
    }

    @Override
    public void use(Character player){
        if(!hasConsumed){
            PhysicalDamageBoost effect = new PhysicalDamageBoost(physicalDamageAmount);
            effect.applyEffect(player);
            player.addStatusEffect(effect);
            System.out.println("💪 " + player.getName() + "'s physical damage increased by " + (physicalDamageAmount * 100) + "%!");
            setQuantity(getQuantity() - 1);
            hasConsumed = true;
        }else{
            System.out.println("You can only consume this potion once!");
        }
    }

    @Override
    public void displayInfo(){
        DecimalFormat df = new DecimalFormat("####");
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println("\"Increases physical damage by " + (df.format(physicalDamageAmount * 100)) + "%. Can only be used once before/during battle\"");
    }
}