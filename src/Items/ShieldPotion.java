package Items;

import Entities.Characters.Character;
import StatusEffects.ShieldEffect;

public class ShieldPotion extends Consumable{
    int shieldAmount;

    public ShieldPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int shieldAmount) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.shieldAmount = shieldAmount;
    }

    @Override
    public void use(Character player){
        ShieldEffect effect = new ShieldEffect(shieldAmount);
        effect.applyEffect(player);
        player.addStatusEffect(effect);
        System.out.println("🛡️ " + player.getName() + " gained " + shieldAmount + " shield!");
        setQuantity(getQuantity() - 1);
    }

    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+getQuantity()+")");
        System.out.println(description);
        System.out.println("Grants " + shieldAmount + " shield for the current battle");
    }
}