package Items;

import Entities.Characters.Character;

public class Inventory {
    protected int maxCapacity;
    protected int capacity = 0;
    protected Item[] items = new Item[maxCapacity];
    protected boolean isFull;

    public Inventory() {
        this.maxCapacity = 10;
        this.items = new Item[maxCapacity];
        this.isFull = false;
    }

    public void setItem(Item item, int index) {
        this.items[index] = item;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }


    public int getMaxCapacity() {
        return maxCapacity;
    }
    public int getCapacity(){
        return capacity;
    }
    public Item[] getItems() {
        return items;
    }
    public boolean getIsFull(){
        return isFull;
    }
}
