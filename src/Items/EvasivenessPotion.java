package Items;

import Entities.Characters.Character;
import StatusEffects.EvasivenessBoost;
import StatusEffects.SpeedBoost;

import java.text.DecimalFormat;

public class EvasivenessPotion extends Consumable{
    double accuracyReduction;

    public EvasivenessPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double accuracyReduction) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.accuracyReduction = accuracyReduction;
    }

    @Override
    public void use(Character player){
        if(hasConsumed == false){
            EvasivenessBoost effect = new EvasivenessBoost(accuracyReduction);
            player.addStatusEffect(effect);
            System.out.println("🌪️ " + player.getName() + "'s evasiveness increased! Enemy accuracy reduced by " + (accuracyReduction * 100) + "%!");
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
        System.out.println("Reduces enemy accuracy by " + (df.format(accuracyReduction * 100)) + "%. Can only be used once before/during battle");
    }
}