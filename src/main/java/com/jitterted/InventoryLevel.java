package com.jitterted;

public class InventoryLevel {
    private int inventory;

    public InventoryLevel(int initialInventory) {
        this.inventory = initialInventory;
    }

    public int level() {
        return inventory;
    }

    boolean hasSufficientInventoryFor(int amountNeeded) {
        return level() < amountNeeded;
    }

    public void updateLevel(int i) {
        this.inventory = i;
    }

    void reduceLevelBy(int qtyNeeded) {
        updateLevel(level() - qtyNeeded);
    }

    void reset() {
        this.inventory = 10;
    }
}
