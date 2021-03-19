package com.jitterted;

public class Ingredient implements Comparable<Ingredient> {
    private final IngredientName name;
    private final double cost; //Dollars, Units, Bitcoin, Rupies, Pounds?
    private InventoryLevel stock = new InventoryLevel(0);

    public Ingredient(IngredientName name, double cost) {
        this(name, cost, 10);
    }

    public Ingredient(IngredientName name, double cost, int initialStock) {
        this.name = name;
        this.cost = cost;
        this.stock = new InventoryLevel(initialStock);
    }

    public int compareTo(Ingredient ingredient) {
        return name.compareTo(ingredient.name);
    }

    public double getCost() {
        return cost;
    }

    public IngredientName getName() {
        return name;
    }

    boolean checkInventoryCapacity(Recipe recipe) {
        return insufficientInventoryFor(recipe);
    }

    private boolean insufficientInventoryFor(Recipe recipe) {
        int amountNeeded = recipe.quantityNeededFor(this);
        return stock.hasSufficientInventoryFor(amountNeeded);
    }

    void reduceInventory(Recipe recipe) {
        int qtyNeeded = recipe.quantityNeededFor(this);
        stock.reduceLevelBy(qtyNeeded);
    }

    void refillStockFor() {
        this.stock.reset();
    }

    String showStock() {
        return String.valueOf(stock);
    }
}
