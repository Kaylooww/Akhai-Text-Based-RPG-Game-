package Items;

import java.util.List;

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

    public void cleanInventory() {
        List<Item> itemsToRemove = new java.util.ArrayList<>();

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getQuantity() <= 0) {
                itemsToRemove.add(items[i]);
            }
        }

        for (Item item : itemsToRemove) {
            removeItem(item);
        }

        updateFullStatus();
    }

    private void updateFullStatus() {
        this.isFull = (capacity >= maxCapacity);
    }

    //remove an item from inventory
    public void removeItem(Item itemToRemove) {
        // Find the item and remove it
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].equals(itemToRemove)) {
                items[i] = null;
                capacity--;

                // Reorganize the array to remove null gaps
                reorganizeInventory();
                break;
            }
        }
    }

    // Helper method to reorganize the inventory after removal
    private void reorganizeInventory() {
        Item[] newItems = new Item[maxCapacity];
        int newIndex = 0;

        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                newItems[newIndex] = items[i];
                newIndex++;
            }
        }

        items = newItems;
    }
}