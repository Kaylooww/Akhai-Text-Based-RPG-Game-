package Items;

import Entities.Characters.Character;

public abstract class Item {
    protected String itemId;
    protected String name;
    protected String description;
    protected ItemType  itemType;
    protected int maxStack;
    protected int quantity;
    protected int value;
    protected Rarity rarity;
    protected boolean isUsable;
    protected boolean isEquippable;

    public Item(){
        this.itemId = "None";
        this.name = "None";
        this.description = "None";
        this.maxStack = 0;
        this.quantity = 0;
        this.value = 0;
        this.isUsable = false;
        this.isEquippable = false;
    }
    public Item(String itemId, String name, String description, int maxStack, int quantity, int value, Rarity rarity, boolean isUsable, boolean isEquippable) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.maxStack = maxStack;
        this.quantity = quantity;
        this.value = value;
        this.rarity = rarity;
        this.isUsable = isUsable;
        this.isEquippable = isEquippable;
    }

    public abstract void use(Character player);
    public abstract void displayInfo();

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setRarity(Rarity rarity){
        this.rarity = rarity;
    }

    public String getItemId() {
        return itemId;
    }
    public String getName(){
        return name;
    }
    public ItemType getItemType() {
        return itemType;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getValue(){
        return value;
    }
    public int getMaxStack(){
        return maxStack;
    }
    public Rarity getRarity(Rarity rarity){
        return rarity;
    }
}
