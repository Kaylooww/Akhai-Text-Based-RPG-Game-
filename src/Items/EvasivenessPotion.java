package Items;

import Entities.Characters.Character;

public class EvasivenessPotion extends  Consumable{
    int evasivenessAmount;

    public EvasivenessPotion(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, int evasivenessAmount) {
        super(itemId, name, description, maxStack, quantity, value, rarity, true, false);
        this.evasivenessAmount = evasivenessAmount;
    }

    @Override
    public void use(Character player){
        //Formula/Calculation for adding evasiveness; FOR YOU ZEDJY!!!
        //quantity--; //Uncomment once the formula is added
    }
    @Override
    public void displayInfo(){
        System.out.println(name+" (x"+quantity+")");
        System.out.println(description);
    }
}
