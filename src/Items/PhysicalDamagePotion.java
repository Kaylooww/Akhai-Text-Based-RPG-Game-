package Items;

import Entities.Characters.Character;

public class PhysicalDamagePotion extends Consumable{
    double physicalDamageAmount;

    public PhysicalDamagePotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double physicalDamageAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.physicalDamageAmount = physicalDamageAmount;
    }

    @Override
    public void use(Character player){
        //Temporarily boosts the stat during combat. Item can be consumed before/during combat but removes the boosted stat after combat (Utilizing the StatusEffect class)
        quantity--;
    }
    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+quantity+")");
        System.out.println(description);
    }
}
