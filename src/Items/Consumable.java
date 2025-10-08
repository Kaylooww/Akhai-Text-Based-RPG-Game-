package Items;

import Entities.Characters.Character;

public abstract class Consumable extends Item {
    public Consumable(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, boolean isUsable, boolean isEquippable){
        super(itemId, name, description, maxStack, quantity, value, rarity, isUsable, isEquippable);
        itemType = ItemType.CONSUMABLE;
    }

    public abstract void use(Character player);
    public abstract void displayInfo();
}
