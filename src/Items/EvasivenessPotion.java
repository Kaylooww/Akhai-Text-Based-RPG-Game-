package Items;

import Entities.Characters.Character;
import StatusEffects.EvasivenessBoost;

public class EvasivenessPotion extends Consumable{
    double accuracyReduction;

    public EvasivenessPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double accuracyReduction) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.accuracyReduction = accuracyReduction;
    }

    @Override
    public void use(Character player){
        EvasivenessBoost effect = new EvasivenessBoost(accuracyReduction);
        player.addStatusEffect(effect);
        System.out.println("🌪️ " + player.getName() + "'s evasiveness increased! Enemy accuracy reduced by " + (accuracyReduction * 100) + "%!");
        setQuantity(getQuantity() - 1);
    }

    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println(description);
        System.out.println("Reduces enemy accuracy by " + (accuracyReduction * 100) + "% for the current battle");
    }
}