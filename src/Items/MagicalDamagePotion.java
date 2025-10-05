package Items;

import Entities.Characters.Character;

public class MagicalDamagePotion extends Consumable{
    double magicalDamageAmount;

    public MagicalDamagePotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, double magicalDamageAmount){
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.magicalDamageAmount = magicalDamageAmount;
    }

    @Override
    public void use(Character player){
        System.out.println("You used item "+name);
        player.setMagicDamage((int) (player.getMagicDamage()+ magicalDamageAmount));
        quantity--;
    }
    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+quantity+")");
        System.out.println(description);
    }
}
